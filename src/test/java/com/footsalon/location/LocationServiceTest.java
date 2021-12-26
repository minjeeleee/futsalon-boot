package com.footsalon.location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LocationServiceTest {

    @Autowired
    LocationService locationService;

    @Test
    @DisplayName("지역 생성")
    public void createLocation() throws Exception {
        Location location = new Location();
        location.setLocalCode("02");
        location.setLocalCity("서울");
        Location savedLocation = locationService.createLocation(location);

        Location foundLocation = locationService.findLocation("02");

        assertThat(foundLocation).isEqualTo(savedLocation);
    }

}