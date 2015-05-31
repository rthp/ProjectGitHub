package com.studio.ranjana;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.studio.ranjana.Issue;
import com.studio.ranjana.User;

public class IssueTest {
    private Issue issueFirst;
    private Issue issueSecond;
    private Issue issueThird;
    private Issue issueFourth;
    private Issue issueFifth;
    private Issue issueSixth;
    private User assignee;
    private User user;

    @Before
    public void setUp() {
        issueFirst = new Issue();
        issueSecond = new Issue();
        issueThird = new Issue();
        issueFourth = new Issue();
        issueFifth = new Issue();
        issueSixth = new Issue();
        assignee = new User();
        user = new User();

        issueFirst.setId(4);
        issueSecond.setId(4);
        issueThird.setId(5);
        issueFourth.setId(4);
        
        issueFifth.setUser(user);
        user.setId(42);
        user.setLogin("rthapali");
        
        issueSixth.setId(2);
        issueSixth.setNumber(12);
        issueSixth.setBody("body");
        issueSixth.setState("open");
        issueSixth.setTitle("title");
        assignee.setLogin("ranju");
        assignee.setId(55);
        issueSixth.setAssignee(assignee);
    }
    
    @Test
    public void issueToString() {
        final String resultTestString = "Issue [number=0, id=4, state=null, title=null,body=null, createdAt=null, closedAt=null, user=null, assignee=null]";
        assertEquals(resultTestString, issueFirst.toString());
        assertNotEquals("Issue [login=Ranjana, id=4]", issueFirst.toString());
        assertEquals("User [login=rthapali, id=42]", issueFifth.getUser()
                .toString());
        assertEquals("title",issueSixth.getTitle());
        assertEquals("body",issueSixth.getBody());
        assertEquals("Issue [number=12, id=2, state=open, title=title,body=body, createdAt=null, closedAt=null, user=null, assignee=User [login=ranju, id=55]]",issueSixth.toString());
    }
    
    @Test
    public void issueHashTest() {
        assertEquals(issueFirst.hashCode(), issueFirst.hashCode());

        assertEquals(true, issueFirst.equals(issueSecond));
        assertEquals(issueFirst.hashCode(), issueSecond.hashCode());
        assertEquals(issueSecond.hashCode(), issueFourth.hashCode());

        assertEquals(false, issueSecond.equals(issueThird));
        assertNotEquals(issueFirst.hashCode(), issueThird.hashCode());
    }
    
    
    @Test
    public void issueEqualsTest() {
        assertEquals(true, issueFirst.equals(issueFirst));

        assertEquals(true, issueFirst.equals(issueSecond));
        assertEquals(true, issueSecond.equals(issueFirst));
        assertEquals(false, issueFirst.equals(issueThird));

        assertEquals(true, issueFirst.equals(issueSecond));
        assertEquals(true, issueSecond.equals(issueFourth));
        assertEquals(true, issueFirst.equals(issueFourth));

        assertEquals(true, issueFirst.equals(issueSecond));
        assertEquals(false, issueFirst.equals(issueThird));
        assertEquals(false, issueSecond.equals(issueThird));

        assertEquals(false, issueFourth.equals(null));
    }
    
    @Test
    public void issueComparableTest() {
        assertEquals(0,issueFirst.compareTo(issueSecond));
        assertEquals(1,issueFirst.compareTo(issueSixth));
        assertEquals(-1, issueSixth.compareTo(issueFirst));
        assertEquals(issueSecond.compareTo(issueFirst), issueFirst.compareTo(issueSecond));
    }
}
