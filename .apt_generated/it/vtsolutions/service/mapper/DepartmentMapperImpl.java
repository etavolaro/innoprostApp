package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Department;
import it.vtsolutions.domain.Location;
import it.vtsolutions.service.dto.DepartmentDTO;
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
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public List<Department> toEntity(List<DepartmentDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Department> list = new ArrayList<Department>( dtoList.size() );
        for ( DepartmentDTO departmentDTO : dtoList ) {
            list.add( toEntity( departmentDTO ) );
        }

        return list;
    }

    @Override
    public List<DepartmentDTO> toDto(List<Department> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>( entityList.size() );
        for ( Department department : entityList ) {
            list.add( toDto( department ) );
        }

        return list;
    }

    @Override
    public DepartmentDTO toDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        Long id = departmentLocationId( department );
        if ( id != null ) {
            departmentDTO.setLocationId( id );
        }
        departmentDTO.setId( department.getId() );
        departmentDTO.setDepartmentName( department.getDepartmentName() );

        return departmentDTO;
    }

    @Override
    public Department toEntity(DepartmentDTO departmentDTO) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setLocation( locationMapper.fromId( departmentDTO.getLocationId() ) );
        department.setId( departmentDTO.getId() );
        department.setDepartmentName( departmentDTO.getDepartmentName() );

        return department;
    }

    private Long departmentLocationId(Department department) {
        if ( department == null ) {
            return null;
        }
        Location location = department.getLocation();
        if ( location == null ) {
            return null;
        }
        Long id = location.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
