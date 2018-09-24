package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Employee;
import it.vtsolutions.domain.Job;
import it.vtsolutions.domain.Task;
import it.vtsolutions.service.dto.JobDTO;
import it.vtsolutions.service.dto.TaskDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:39+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Job> toEntity(List<JobDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Job> list = new ArrayList<Job>( dtoList.size() );
        for ( JobDTO jobDTO : dtoList ) {
            list.add( toEntity( jobDTO ) );
        }

        return list;
    }

    @Override
    public List<JobDTO> toDto(List<Job> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<JobDTO> list = new ArrayList<JobDTO>( entityList.size() );
        for ( Job job : entityList ) {
            list.add( toDto( job ) );
        }

        return list;
    }

    @Override
    public JobDTO toDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDTO jobDTO = new JobDTO();

        Long id = jobEmployeeId( job );
        if ( id != null ) {
            jobDTO.setEmployeeId( id );
        }
        jobDTO.setId( job.getId() );
        jobDTO.setJobTitle( job.getJobTitle() );
        jobDTO.setMinSalary( job.getMinSalary() );
        jobDTO.setMaxSalary( job.getMaxSalary() );
        jobDTO.setTasks( taskSetToTaskDTOSet( job.getTasks() ) );

        return jobDTO;
    }

    @Override
    public Job toEntity(JobDTO jobDTO) {
        if ( jobDTO == null ) {
            return null;
        }

        Job job = new Job();

        job.setEmployee( employeeMapper.fromId( jobDTO.getEmployeeId() ) );
        job.setId( jobDTO.getId() );
        job.setJobTitle( jobDTO.getJobTitle() );
        job.setMinSalary( jobDTO.getMinSalary() );
        job.setMaxSalary( jobDTO.getMaxSalary() );
        job.setTasks( taskDTOSetToTaskSet( jobDTO.getTasks() ) );

        return job;
    }

    private Long jobEmployeeId(Job job) {
        if ( job == null ) {
            return null;
        }
        Employee employee = job.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<TaskDTO> taskSetToTaskDTOSet(Set<Task> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskDTO> set1 = new HashSet<TaskDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Task task : set ) {
            set1.add( taskMapper.toDto( task ) );
        }

        return set1;
    }

    protected Set<Task> taskDTOSetToTaskSet(Set<TaskDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Task> set1 = new HashSet<Task>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskDTO taskDTO : set ) {
            set1.add( taskMapper.toEntity( taskDTO ) );
        }

        return set1;
    }
}
