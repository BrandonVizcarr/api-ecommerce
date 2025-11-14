package com.api_ecommerce.services.implementation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.RateRequestDTO;
import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.entities.Rating;
import com.api_ecommerce.entities.Seller;
import com.api_ecommerce.entities.User;
import com.api_ecommerce.repositories.RatingRepository;
import com.api_ecommerce.repositories.SellerRepository;
import com.api_ecommerce.repositories.UserRepository;
import com.api_ecommerce.services.SellerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RatingRepository ratingRepository;

    @Override
    @Transactional
    public Seller saveSeller(SellerRequestDTO sellerRequestDTO) {
        if (sellerRepository.existsByName(sellerRequestDTO.getName())) {
            throw new EntityExistsException("Seller already exists with name: " + sellerRequestDTO.getName());
        }

        User user = userRepository.findById(sellerRequestDTO.getUserId())
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found with ID: " + sellerRequestDTO.getUserId()));

        if (sellerRepository.existsByUserId(user.getUserId())) {
            throw new EntityExistsException("This user is already registered as a seller.");
        }
        user.setRole(2);
        userRepository.save(user);

        Seller seller = modelMapper.map(sellerRequestDTO, Seller.class);

        return sellerRepository.save(seller);
    }

    @Override
    public Seller getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found: " + sellerId));
    }

    @Override
    public Page<Seller> getSellers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return sellerRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Seller updateSeller(Long sellerId, SellerRequestDTO sellerRequestDTO) {
        return sellerRepository.findById(sellerId)
                .map(existing -> {
                    modelMapper.map(sellerRequestDTO, existing);
                    return sellerRepository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("Seller not found: " + sellerId));
    }

    @Override
    @Transactional
    public Seller patchSeller(Long sellerId, RateRequestDTO rateRequestDTO) {
        UUID userId = rateRequestDTO.getUserId();
        Double rateValue = rateRequestDTO.getRate();

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found: " + sellerId));

        boolean exists = ratingRepository.existsByUserIdAndTargetTypeAndTargetId(
                userId, "SELLER", sellerId.toString());

        if (exists) {
            throw new IllegalStateException("User has already rated this seller");
        }

        Rating rating = new Rating();
        rating.setUserId(userId);
        rating.setTargetType("SELLER");
        rating.setTargetId(sellerId.toString());
        rating.setValue(rateValue);
        ratingRepository.save(rating);

        int count = seller.getRateCount() != null ? seller.getRateCount() : 0;
        double sum = seller.getRate() * count;

        count++;
        sum += rateValue;

        seller.setRate(sum / count);
        seller.setRateCount(count);

        return sellerRepository.save(seller);
    }

    @Override
    public Boolean deleteSeller(Long sellerId) {
        return sellerRepository.findById(sellerId).map(
            existing->{
                sellerRepository.deleteById(sellerId);
                return true;
            }
        ).orElseThrow(()-> new EntityNotFoundException("Seller not found: "+sellerId));
    }

}
