package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Region;
import it.vtsolutions.service.dto.RegionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:38+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public Region toEntity(RegionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( dto.getId() );
        region.setRegionName( dto.getRegionName() );

        return region;
    }

    @Override
    public RegionDTO toDto(Region entity) {
        if ( entity == null ) {
            return null;
        }

        RegionDTO regionDTO = new RegionDTO();

        regionDTO.setId( entity.getId() );
        regionDTO.setRegionName( entity.getRegionName() );

        return regionDTO;
    }

    @Override
    public List<Region> toEntity(List<RegionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Region> list = new ArrayList<Region>( dtoList.size() );
        for ( RegionDTO regionDTO : dtoList ) {
            list.add( toEntity( regionDTO ) );
        }

        return list;
    }

    @Override
    public List<RegionDTO> toDto(List<Region> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RegionDTO> list = new ArrayList<RegionDTO>( entityList.size() );
        for ( Region region : entityList ) {
            list.add( toDto( region ) );
        }

        return list;
    }
}
