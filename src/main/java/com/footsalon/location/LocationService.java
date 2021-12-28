package com.footsalon.location;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationService {

    private final LocationRepository locationRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location findLocation(String localCode) {
        return locationRepository.findById(localCode).orElseThrow(()-> new HandlableException(ErrorCode.LOCAL_CODE_DOES_NOT_EXIST));
    }
}
