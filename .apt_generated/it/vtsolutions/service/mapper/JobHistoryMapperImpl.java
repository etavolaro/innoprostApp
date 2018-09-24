package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Department;
import it.vtsolutions.domain.Employee;
import it.vtsolutions.domain.Job;
import it.vtsolutions.domain.JobHistory;
import it.vtsolutions.service.dto.JobHistoryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:37+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class JobHistoryMapperImpl implements JobHistoryMapper {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<JobHistory> toEntity(List<JobHistoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<JobHistory> list = new ArrayList<JobHistory>( dtoList.size() );
        for ( JobHistoryDTO jobHistoryDTO : dtoList ) {
            list.add( toEntity( jobHistoryDTO ) );
        }

        return list;
    }

    @Override
    public List<JobHistoryDTO> toDto(List<JobHistory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<JobHistoryDTO> list = new ArrayList<JobHistoryDTO>( entityList.size() );
        for ( JobHistory jobHistory : entityList ) {
            list.add( toDto( jobHistory ) );
        }

        return list;
    }

    @Override
    public JobHistoryDTO toDto(JobHistory jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }

        JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();

        Long id = jobHistoryJobId( jobHistory );
        if ( id != null ) {
            jobHistoryDTO.setJobId( id );
        }
        Long id1 = jobHistoryEmployeeId( jobHistory );
        if ( id1 != null ) {
            jobHistoryDTO.setEmployeeId( id1 );
        }
        Long id2 = jobHistoryDepartmentId( jobHistory );
        if ( id2 != null ) {
            jobHistoryDTO.setDepartmentId( id2 );
        }
        jobHistoryDTO.setId( jobHistory.getId() );
        jobHistoryDTO.setStartDate( jobHistory.getStartDate() );
        jobHistoryDTO.setEndDate( jobHistory.getEndDate() );
        jobHistoryDTO.setLanguage( jobHistory.getLanguage() );

        return jobHistoryDTO;
    }

    @Override
    public JobHistory toEntity(JobHistoryDTO jobHistoryDTO) {
        if ( jobHistoryDTO == null ) {
            return null;
        }

        JobHistory jobHistory = new JobHistory();

        jobHistory.setJob( jobMapper.fromId( jobHistoryDTO.getJobId() ) );
        jobHistory.setDepartment( departmentMapper.fromId( jobHistoryDTO.getDepartmentId() ) );
        jobHistory.setEmployee( employeeMapper.fromId( jobHistoryDTO.getEmployeeId() ) );
        jobHistory.setId( jobHistoryDTO.getId() );
        jobHistory.setStartDate( jobHistoryDTO.getStartDate() );
        jobHistory.setEndDate( jobHistoryDTO.getEndDate() );
        jobHistory.setLanguage( jobHistoryDTO.getLanguage() );

        return jobHistory;
    }

    private Long jobHistoryJobId(JobHistory jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }
        Job job = jobHistory.getJob();
        if ( job == null ) {
            return null;
        }
        Long id = job.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long jobHistoryEmployeeId(JobHistory jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }
        Employee employee = jobHistory.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long jobHistoryDepartmentId(JobHistory jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }
        Department department = jobHistory.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
