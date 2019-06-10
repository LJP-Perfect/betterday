package me.freelee.betterday.service;

import me.freelee.betterday.model.Team;
import me.freelee.betterday.model.User;

import java.util.List;

public interface TeamService {
    int insertTeam(Team team);

    List<Team> selectTeamsByCapitalId(Integer userId);

    List<Team> selectTeamsByTeammate(Integer userId);

    List<Team> selectAll();

    Team selectTeamByName(String teamName);

    void updateTeam(Team team);

    Team selectTeamByID(Integer teamId);

    void deleteTeam(Integer teamId);

    List<User> selectTeammateIds(Integer teamId);
}
