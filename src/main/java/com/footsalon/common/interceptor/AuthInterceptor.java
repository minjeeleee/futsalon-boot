package com.footsalon.common.interceptor;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.code.member.MemberGrade;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.member.Member;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String[] uriArr = request.getRequestURI().split("/");

        if (uriArr.length != 0) {
            switch (uriArr[1]) {
                case "mypage":
                    if (request.getSession().getAttribute("authentication") == null) {
                        throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
                    }
                    mypageAuthorize(request, response, uriArr);
                    break;
                case "team":
                    teamAuthorize(request, response, uriArr);
                    break;
                case "matching":
                    matchingAuthorize(request, response, uriArr);
                    break;
                case "member":
                    memberAuthorize(request, response, uriArr);
                    break;
                case "notice":
                    noticeAuthorize(request, response, uriArr);
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private void noticeAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

        switch (uriArr[2]) {
            case "notice-posting":

                Member member = (Member) httpRequest.getSession().getAttribute("authentication");
                MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

                if (!adminGrade.ROLE.equals("admin")) {
                    throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
                }
                break;
            default:
                break;
        }

    }

    private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

        String serverToken = (String) httpRequest.getSession().getAttribute("persist-token");
        String clientToken = httpRequest.getParameter("persist-token");

        switch (uriArr[2]) {
            case "join-impl":
                if (serverToken == null || !serverToken.equals(clientToken)) {
                    throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
                }
                break;
            default:
                break;
        }

    }

    private void matchingAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr)
            throws ServletException, IOException {

        switch (uriArr[2]) {
            case "list-up":
                Member member = (Member) httpRequest.getSession().getAttribute("authentication");

                MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

                if (!adminGrade.DESC.equals("leader")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            default:
                matchingDetailAuthorize(httpRequest, httpResponse, uriArr);
                break;
        }
    }

    private void matchingDetailAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                         String[] uriArr) {

        switch (uriArr[3]) {
            case "mercenary-list":
                break;
            case "team-list":
                break;
            default:
                if (httpRequest.getSession().getAttribute("authentication") == null) {
                    throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
                }
                matchingOnlyLeaderAuthorize(httpRequest, httpResponse, uriArr);
                break;
        }
    }

    private void matchingOnlyLeaderAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                             String[] uriArr) {

        Member member = (Member) httpRequest.getSession().getAttribute("authentication");
        MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

        switch (uriArr[3]) {

            case "mercenary-match-form":
                if (!adminGrade.DESC.equals("leader")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            case "mercenary-modify":
                if (!adminGrade.DESC.equals("leader")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            case "team-match-form":
                if (!adminGrade.DESC.equals("leader")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            case "team-modify":
                if (!adminGrade.DESC.equals("leader")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            default:
                break;
        }

    }

    private void teamAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
        Member member = (Member) httpRequest.getSession().getAttribute("authentication");
        if(member == null) {
            throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
        }
       // TeamService ts = new TeamService();
        //Team team = ts.selectTeamByUserId(member.getUserId());
        /*if(team!=null) {
            team.setTmRating(ts.selectTmAvgRating(team.getTmCode()));
        }*/
        //httpRequest.getSession().setAttribute("team", team);

        MemberGrade grade = MemberGrade.valueOf(member.getGrade());
        switch (uriArr[2]) {
            case "managing":
                if(!grade.ROLE.equals("team")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            case "create-form":
                if(!grade.ROLE.equals("user")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            case "join-team":
                if(!grade.ROLE.equals("user")) {
                    throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
                }
                break;
            default: break;
        }
    }

    private void mypageAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

        Member member = (Member) httpRequest.getSession().getAttribute("authentication");
        MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

        switch (uriArr[2]) {
            case "support":
                if (member == null) {
                    throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
                }
                break;
            default:
                break;
        }

    }

}
