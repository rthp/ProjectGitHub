package com.studio.ranjana;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.studio.ranjana.User;

public class UserTest {
    private User userFirst;
    private User userSecond;
    private User userThird;
    private User userFourth;

    @Before
    public void setUp() {
        userFirst = new User();
        userSecond = new User();
        userThird = new User();
        userFourth = new User();

        userFirst.setId(4);
        userFirst.setLogin("ranjana");
        
        userSecond.setId(4);
        userThird.setId(5);
        userFourth.setId(4);

    }

    @Test
    public void userToString() {
        assertEquals("User [login=ranjana, id=4]", userFirst.toString());
        assertNotEquals("User [login=Ranjana, id=4]", userFirst.toString());
    }

    @Test
    public void userHashTest() {
        assertEquals(userFirst.hashCode(), userFirst.hashCode());

        assertEquals(true, userFirst.equals(userSecond));
        assertEquals(userFirst.hashCode(), userSecond.hashCode());
        assertEquals(userSecond.hashCode(), userFourth.hashCode());

        assertEquals(false, userSecond.equals(userThird));
        assertNotEquals(userFirst.hashCode(), userThird.hashCode());
    }

    @Test
    public void userEqualsTest() {
        assertEquals(true, userFirst.equals(userFirst));

        assertEquals(true, userFirst.equals(userSecond));
        assertEquals(true, userSecond.equals(userFirst));
        assertEquals(false, userFirst.equals(userThird));

        assertEquals(true, userFirst.equals(userSecond));
        assertEquals(true, userSecond.equals(userFourth));
        assertEquals(true, userFirst.equals(userFourth));

        assertEquals(true, userFirst.equals(userSecond));
        assertEquals(false, userFirst.equals(userThird));
        assertEquals(false, userSecond.equals(userThird));

        assertEquals(false, userFourth.equals(null));
    }

}
