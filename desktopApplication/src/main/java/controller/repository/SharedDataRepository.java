package controller.repository;

import controller.entity.Gender;
import controller.entity.Person;
import lombok.Data;
import model.ConsumerAPI;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SharedDataRepository {

    private static final SharedDataRepository INSTATNCE = new SharedDataRepository();

//    public static final String urlPerson = "http://localhost:8080/api/v1/person/";
//    public static final String urlGender = "http://localhost:8080/api/v1/gender/";

    public static final String urlPerson = "https://nutcachechallenge-kayropereira.herokuapp.com/api/v1/person/";
    public static final String urlGender = "https://nutcachechallenge-kayropereira.herokuapp.com/api/v1/gender/";

    private List<Person> persons;
    private List<Gender> genders;

    private final ConsumerAPI consumerAPI = new ConsumerAPI();

    private SharedDataRepository(){
        this.persons = consumerAPI.get(urlPerson, Person.class);
        this.genders = consumerAPI.get(urlGender, Gender.class);
    }

    public static SharedDataRepository getInstance(){
        return INSTATNCE;
    }

    public Long getIdGenderByName(String description){
        return genders.stream()
                .filter(g -> g.getDescription().equalsIgnoreCase(description))
                .collect(Collectors.toList())
                .get(0).getId();
    }
}
