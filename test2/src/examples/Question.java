package examples;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ravil
 */
    public class Question
    {
        public Question(String[] s)
        {
            Text = s[0];
            for (int i=0; i<4; i++)
                Answers[i]= s[i+1];
            RightAnswer=s[5];
            Level = Integer.parseInt(s[6]);
        }
        public String Text;
        String[] Answers = new String[4];
        public String RightAnswer;
        public int Level;
    }
