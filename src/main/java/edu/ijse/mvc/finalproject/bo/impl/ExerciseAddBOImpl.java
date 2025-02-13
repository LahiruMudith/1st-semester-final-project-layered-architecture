package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.ExerciseAddBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.ExerciseDAO;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.entity.Exercise;

import java.sql.SQLException;

public class ExerciseAddBOImpl implements ExerciseAddBO {
    ExerciseDAO exerciseDAO = (ExerciseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXERCISE);
    @Override
    public boolean save(ExerciseDto exerciseDto) throws SQLException, ClassNotFoundException {
        return exerciseDAO.save(new Exercise(
                exerciseDto.getExercise_id(),
                exerciseDto.getExercise_name(),
                exerciseDto.getDescription()
        ));
    }
}
