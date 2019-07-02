package Model;

public class Review
{
    private String text;
    private Double rating;
    private int id;

    public String getText() {
        return text;
    }

    public Double getRating() {
        return rating;
    }



    public int getId() {
        return id;
    }

    public Review( int id, String text, Double rating)
    {
        this.id = id;
        this.text = text;
        this.rating = rating;
    }
}
