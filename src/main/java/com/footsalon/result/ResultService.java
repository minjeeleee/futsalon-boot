package com.footsalon.result;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.match.dto.ResultRequest;
import com.footsalon.team.Team;
import com.footsalon.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResultService {

    private final ResultRepository resultRepository;

    public Result findResultByTmIdx(long thIdx) {
        return resultRepository.findById(thIdx).orElseThrow(()-> new HandlableException(ErrorCode.RESULT_DOES_NOT_EXIST));
    }

    @Transactional
    public Result createResult(Team winner) {
        Result result = Result.createResult(winner);
        return resultRepository.save(result);
    }

    @Transactional
    public void updateRating(Long thIdx, String target, int rating) {
        Result result = resultRepository.findById(thIdx).orElseThrow(()-> new HandlableException(ErrorCode.RESULT_DOES_NOT_EXIST));
        if (target.equals("homeTeam")) {
            result.setHomeRating(rating);
        } else {
            result.setAwayRating(rating);
        }
    }
}
