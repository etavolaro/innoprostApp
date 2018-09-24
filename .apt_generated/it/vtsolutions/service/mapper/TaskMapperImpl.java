package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.Task;
import it.vtsolutions.service.dto.TaskDTO;
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
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( entity.getId() );
        taskDTO.setTitle( entity.getTitle() );
        taskDTO.setDescription( entity.getDescription() );

        return taskDTO;
    }

    @Override
    public List<Task> toEntity(List<TaskDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( dtoList.size() );
        for ( TaskDTO taskDTO : dtoList ) {
            list.add( toEntity( taskDTO ) );
        }

        return list;
    }

    @Override
    public List<TaskDTO> toDto(List<Task> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( entityList.size() );
        for ( Task task : entityList ) {
            list.add( toDto( task ) );
        }

        return list;
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setTitle( taskDTO.getTitle() );
        task.setDescription( taskDTO.getDescription() );

        return task;
    }
}
