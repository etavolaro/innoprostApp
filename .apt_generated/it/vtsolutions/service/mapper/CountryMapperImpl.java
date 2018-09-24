package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Country;
import it.vtsolutions.domain.Region;
import it.vtsolutions.service.dto.CountryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:38+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Country> toEntity(List<CountryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Country> list = new ArrayList<Country>( dtoList.size() );
        for ( CountryDTO countryDTO : dtoList ) {
            list.add( toEntity( countryDTO ) );
        }

        return list;
    }

    @Override
    public List<CountryDTO> toDto(List<Country> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CountryDTO> list = new ArrayList<CountryDTO>( entityList.size() );
        for ( Country country : entityList ) {
            list.add( toDto( country ) );
        }

        return list;
    }

    @Override
    public CountryDTO toDto(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryDTO countryDTO = new CountryDTO();

        Long id = countryRegionId( country );
        if ( id != null ) {
            countryDTO.setRegionId( id );
        }
        countryDTO.setId( country.getId() );
        countryDTO.setCountryName( country.getCountryName() );

        return countryDTO;
    }

    @Override
    public Country toEntity(CountryDTO countryDTO) {
        if ( countryDTO == null ) {
            return null;
        }

        Country country = new Country();

        country.setRegion( regionMapper.fromId( countryDTO.getRegionId() ) );
        country.setId( countryDTO.getId() );
        country.setCountryName( countryDTO.getCountryName() );

        return country;
    }

    private Long countryRegionId(Country country) {
        if ( country == null ) {
            return null;
        }
        Region region = country.getRegion();
        if ( region == null ) {
            return null;
        }
        Long id = region.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
