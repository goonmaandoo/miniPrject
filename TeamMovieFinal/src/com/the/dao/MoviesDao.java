package com.the.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.the.dto.MoviesDto;
import com.the.util.DBConn;
import com.the.util.UserInput;

public class MoviesDao {
   
   public static ArrayList<MoviesDto> selectAll() {
      ArrayList<MoviesDto> dtos =
            new ArrayList<MoviesDto>();
      String sql="select * from movies order by movie_id";
      ResultSet rs=DBConn.statementQuery(sql);
      try{
         while(rs.next()) {
            dtos.add(new MoviesDto(
                  rs.getLong("movie_id"),
                  rs.getString("movie_name"),
                  rs.getString("genre"),
                  rs.getDate("release_date").toLocalDate()));
         };
      }catch(Exception e) {
         e.printStackTrace();
      }
      return dtos;
   }

   public static void delete(long movieId) {
      DBConn.statementUpdate("delete from movies where movie_id="+movieId);
   }
   public static void update(long movieId, String movieName, 
         String genre, LocalDate releaseDate) {
      String sql = String.format("update movies set movie_name='%s', "
            + "genre ='%s', release_Date='%s' where movie_id=%d",
            movieName, genre, UserInput.dateToString(releaseDate), movieId);
      DBConn.statementUpdate(sql);
   }
   public static void insert(MoviesDto dto) {
         String sql = String.format(
               "insert into movies (movie_Name, genre, release_Date) "
                     + "values ('%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'))",
               dto.getMovieName(), dto.getGenre(),
               UserInput.dateToString(dto.getReleaseDate()));
         DBConn.statementUpdate(sql);
   }
   
   
   // id로 movies 테이블 조회
   public static MoviesDto findbyId(long movieId) {
      MoviesDto dtos = null;
      String sql = String.format("select * from movies where movie_id = %d", movieId);
      ResultSet rs = DBConn.statementQuery(sql);

      try {
         if (rs.next()) {
            dtos = new MoviesDto(rs.getString("movie_Name"), rs.getString("genre"),
                  rs.getDate("release_Date").toLocalDate());
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return dtos;
   }

   
}