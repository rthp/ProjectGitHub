package com.studio.ranjana;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IssuesExporter {
    private static final String URL_END = "-private-repo/issues";
    private static final String STATE_CLOSED = "?state=closed";
    private static final String INVALID_JSON = "Invalid";
    private String githubUserName;
    private String githubPassword;
    private List<Issue> combinedIssues;
    private String openIssues;
    private String closedIssues;
    private String repoName;
    private static PrintWriter issuesFile = null;
    private GitHubRestClient prototype = new GitHubRestClient();
    private IssueParser newParseIssues = new IssueParser();
    private static final String EXTRACTING_OPEN_ISSUES = "Extracting open issues...";
    private static final String EXTRACTING_CLOSED_ISSUES = "Extracting closed issues...";
    
    private static final String ISSUES_URL = "/repos/Villanova-SoftwareStudio-Spring2014/";

    public void newUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your github username: ");
        githubUserName = input.next();

        System.out.println("Enter your github password:");
        githubPassword = input.next();

        System.out.println("Enter your github repository name: ");
        repoName = input.next();
        input.close();
        getAndPrintIssues();
    }

    protected void getAndPrintIssues() {
        openIssues = requestOpenIssues();
        closedIssues = requestClosedIssues();
        checkIssues();
    }

    protected String requestOpenIssues() {
        String openIssuesString = prototype.requestIssues(githubUserName,
                githubPassword, ISSUES_URL + repoName + URL_END);
        System.out.println(EXTRACTING_OPEN_ISSUES);
        return openIssuesString;
    }

    protected String requestClosedIssues() {
        String closedIssuesString = prototype.requestIssues(githubUserName,
                githubPassword, ISSUES_URL + repoName + URL_END
                        + STATE_CLOSED);
        System.out.println(EXTRACTING_CLOSED_ISSUES);
        return closedIssuesString;
    }

    protected void checkIssues() {
        if (openIssues == INVALID_JSON || closedIssues == INVALID_JSON) {
            System.out.println("Something went wrong. Please try again.");
        }
        else {
            parseAndSortIssues();
            printIssue(combinedIssues);
        }
    }

    protected List<Issue> parseIssues(String issuesString) {

        return newParseIssues.parseIssues(issuesString);
    }

    protected void parseAndSortIssues() {
        List<Issue> parsedOpenIssues = parseIssues(openIssues);
        List<Issue> parsedClosedIssues = parseIssues(closedIssues);
        combinedIssues = addIssues(parsedOpenIssues, parsedClosedIssues);
        Collections.sort(combinedIssues);
    }

    protected List<Issue> addIssues(List<Issue> parsedOpenIssues,
            List<Issue> parsedClosedIssues) {
        parsedOpenIssues.addAll(parsedClosedIssues);
        return parsedOpenIssues;
    }

    public void printIssue(List<Issue> issueList) {
        try {
            issuesFile = new PrintWriter(new FileWriter("issues.txt"));
            System.lineSeparator();
            System.out.println("Final Output:");
            for (int i = 0; i < issueList.size(); i++) {
                issuesFile.write(issueList.get(i).toString());
                issuesFile.write(System.lineSeparator());
                System.out.println(issueList.get(i).toString());
            }
        }

        catch (IOException ioerror) {
            System.out.println("IO Error");
        }

        finally {
            if (issuesFile != null) {
                System.out.println("Printed and Closing File Writer.");
                issuesFile.close();
            }
            else {
                System.out.println("File Writer Failed");
            }
        }
    }

    public static void main(String[] args) {
        IssuesExporter myIssueExporter = new IssuesExporter();
        myIssueExporter.newUser();
    }
}
