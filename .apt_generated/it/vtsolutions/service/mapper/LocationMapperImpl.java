package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Country;
import it.vtsolutions.domain.Location;
import it.vtsolutions.service.dto.LocationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:36+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Location> toEntity(List<LocationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Location> list = new ArrayList<Location>( dtoList.size() );
        for ( LocationDTO locationDTO : dtoList ) {
            list.add( toEntity( locationDTO ) );
        }

        return list;
    }

    @Override
    public List<LocationDTO> toDto(List<Location> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LocationDTO> list = new ArrayList<LocationDTO>( entityList.size() );
        for ( Location location : entityList ) {
            list.add( toDto( location ) );
        }

        return list;
    }

    @Override
    public LocationDTO toDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();

        Long id = locationCountryId( location );
        if ( id != null ) {
            locationDTO.setCountryId( id );
        }
        locationDTO.setId( location.getId() );
        locationDTO.setStreetAddress( location.getStreetAddress() );
        locationDTO.setPostalCode( location.getPostalCode() );
        locationDTO.setCity( location.getCity() );
        locationDTO.setStateProvince( location.getStateProvince() );

        return locationDTO;
    }

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        if ( locationDTO == null ) {
            return null;
        }

        Location location = new Location();

        location.setCountry( countryMapper.fromId( locationDTO.getCountryId() ) );
        location.setId( locationDTO.getId() );
        location.setStreetAddress( locationDTO.getStreetAddress() );
        location.setPostalCode( locationDTO.getPostalCode() );
        location.setCity( locationDTO.getCity() );
        location.setStateProvince( locationDTO.getStateProvince() );

        return location;
    }

    private Long locationCountryId(Location location) {
        if ( location == null ) {
            return null;
        }
        Country country = location.getCountry();
        if ( country == null ) {
            return null;
        }
        Long id = country.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
