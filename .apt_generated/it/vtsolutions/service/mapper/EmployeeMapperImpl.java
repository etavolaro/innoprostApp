package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Department;
import it.vtsolutions.domain.Employee;
import it.vtsolutions.service.dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:39+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Employee> toEntity(List<EmployeeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( dtoList.size() );
        for ( EmployeeDTO employeeDTO : dtoList ) {
            list.add( toEntity( employeeDTO ) );
        }

        return list;
    }

    @Override
    public List<EmployeeDTO> toDto(List<Employee> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( entityList.size() );
        for ( Employee employee : entityList ) {
            list.add( toDto( employee ) );
        }

        return list;
    }

    @Override
    public EmployeeDTO toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        Long id = employeeDepartmentId( employee );
        if ( id != null ) {
            employeeDTO.setDepartmentId( id );
        }
        Long id1 = employeeManagerId( employee );
        if ( id1 != null ) {
            employeeDTO.setManagerId( id1 );
        }
        employeeDTO.setId( employee.getId() );
        employeeDTO.setFirstName( employee.getFirstName() );
        employeeDTO.setLastName( employee.getLastName() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setPhoneNumber( employee.getPhoneNumber() );
        employeeDTO.setHireDate( employee.getHireDate() );
        employeeDTO.setSalary( employee.getSalary() );
        employeeDTO.setCommissionPct( employee.getCommissionPct() );

        return employeeDTO;
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setManager( fromId( employeeDTO.getManagerId() ) );
        employee.setDepartment( departmentMapper.fromId( employeeDTO.getDepartmentId() ) );
        employee.setId( employeeDTO.getId() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setLastName( employeeDTO.getLastName() );
        employee.setEmail( employeeDTO.getEmail() );
        employee.setPhoneNumber( employeeDTO.getPhoneNumber() );
        employee.setHireDate( employeeDTO.getHireDate() );
        employee.setSalary( employeeDTO.getSalary() );
        employee.setCommissionPct( employeeDTO.getCommissionPct() );

        return employee;
    }

    private Long employeeDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long employeeManagerId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Employee manager = employee.getManager();
        if ( manager == null ) {
            return null;
        }
        Long id = manager.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
