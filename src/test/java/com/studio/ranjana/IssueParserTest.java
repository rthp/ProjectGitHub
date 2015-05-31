package com.studio.ranjana;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.studio.ranjana.Issue;
import com.studio.ranjana.IssueParser;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class IssueParserTest {
    private static String jsonContent = "";

    @Before
    public void setUp() {
        takeInput();
    }

    protected static String takeInput() {
        try {
            Scanner scanner = new Scanner(new File(
                    "../githubapi-issues/src/sample-output.txt"));
            while (scanner.hasNextLine()) {
                jsonContent = jsonContent + scanner.nextLine();
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Your file was not found");
        }
        return jsonContent;
    }

    @Test
    public void doTest() {
        IssueParser issueParsing = new IssueParser();
        List<Issue> issues = issueParsing.parseIssues(jsonContent);
        
        String thirdIssueExpected = "Issue [number=3, id=55556038, state=open, title=Learn GitHub API,body=I am trying to understand how GitHub API works taking example of issues., createdAt=Mon Jan 26 23:58:19 EST 2015, closedAt=null, user=User [login=rthp, id=4101110], assignee=User [login=rthp, id=4101110]]";
        String secondIssueExpected = "Issue [number=4, id=55556472, state=open, title=Issue2,body=This is my second issue. I am adding this to body section, createdAt=Tue Jan 27 00:02:50 EST 2015, closedAt=null, user=User [login=rthp, id=4101110], assignee=null]";
        String fourthIssueExpected = "Issue [number=2, id=55424304, state=open, title=Hw1 done,body=Homework 1 Task 3rd, createdAt=Sun Jan 25 18:29:03 EST 2015, closedAt=null, user=User [login=rthp, id=4101110], assignee=User [login=gingeraltoids, id=1882863]]";
        
        assertEquals(4, issues.size());
        assertEquals(thirdIssueExpected, issues.get(2).toString());
        assertEquals(secondIssueExpected, issues.get(1).toString());
        assertEquals(fourthIssueExpected, issues.get(3).toString());

    }
    
 


}
