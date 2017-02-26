package edu.bsuir.likeit.entity;

import edu.bsuir.likeit.entity.elementsofcontent.ElementOfContent;
import edu.bsuir.likeit.entity.elementsofcontent.Text;

import java.util.LinkedList;

public class Content extends Entity {
    private long id;
    private long questionId;
    private LinkedList<ElementOfContent> contentList;


    public Content(long questionId, LinkedList<ElementOfContent> contentList) {
        this.questionId = questionId;
        this.contentList = contentList;
    }

    public Content(String text) {
        this.contentList = new LinkedList<>();
        this.contentList.add(new Text(text));
    }

    public void add(ElementOfContent element) {
        contentList.add(element);
    }

    public LinkedList<ElementOfContent> getContentList() {
        return contentList;
    }

    public void setContentList(LinkedList<ElementOfContent> contentList) {
        this.contentList = contentList;
    }


    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return contentList.toString();
    }
}
