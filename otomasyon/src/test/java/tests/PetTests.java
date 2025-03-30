package tests;

import base.BaseTest;
import endpoints.PetEndpoint;
import io.restassured.response.Response;
import model.pet.Category;
import model.pet.Pet;
import model.pet.Tag;
import org.testng.annotations.Test;
import payload.PetNegativePayloadGenerator;
import utils.AssertionsHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetTests extends BaseTest {

    int petId = 1232;

    private Pet create_sample_pet(int petId) {
        Category category = new Category(1, "Dog");
        Tag tag = new Tag(1, "Friendly");

        return new Pet(
                petId,
                "Rex",
                "available",
                category,
                Arrays.asList("https://image.com/dog.jpg"),
                Collections.singletonList(tag)
        );
    }

    @Test(priority = 1)
    public void create_pet_positive_test() {
        Pet pet = create_sample_pet(petId);

        Response response =
                given()
                        .body(pet)
                        .post(PetEndpoint.CREATE_PET);

        AssertionsHelper.assert_status_code(response, 200);
        AssertionsHelper.assert_field_value(response, "name", pet.getName());
    }

    @Test(priority = 2)
    public void get_pet_positive_test() {
        Response response =
                given()
                        .pathParam("petId", petId)
                        .get(PetEndpoint.GET_PET);

        AssertionsHelper.assert_status_code(response, 200);
        AssertionsHelper.assert_field_value(response, "id", String.valueOf(petId));
    }

    @Test(priority = 3)
    public void update_pet_positive_test() {
        Pet updatedPet = create_sample_pet(petId);
        updatedPet.setName("UpdatedRex");

        Response response =
                given()
                        .body(updatedPet)
                        .put(PetEndpoint.UPDATE_PET);

        AssertionsHelper.assert_status_code(response, 200);
        AssertionsHelper.assert_field_value(response, "name", updatedPet.getName());
    }

    @Test(priority = 4)
    public void delete_pet_positive_test() {
        Response response =
                given()
                        .pathParam("petId", petId)
                        .delete(PetEndpoint.DELETE_PET);

        AssertionsHelper.assert_status_code(response, 200);
    }

    // Negatif test: eksik alanla pet oluşturma
    @Test(priority = 5)
    public void create_pet_invalid_id_test() {
        Map<String, Object> payload = PetNegativePayloadGenerator.pet_with_invalid_id_type();


        Response response =
                given()
                        .body(payload)
                        .post(PetEndpoint.CREATE_PET);

        AssertionsHelper.assert_status_code(response, 500); //  uygun hata kodu
    }

    // Negatif test: var olmayan id ile pet çağırma
    @Test(priority = 6)
    public void get_non_existing_pet_test() {

        Response response =
                given()
                        .pathParam("petId", 234242)
                        .get(PetEndpoint.GET_PET);

        AssertionsHelper.assert_status_code(response, 404);
    }

    // Negatif test: geçersiz veriyle güncelleme
    @Test(priority = 7)
    public void update_pet_invalid_data_test() {
        Map<String, Object> payload = PetNegativePayloadGenerator.pet_with_invalid_id_type();


        Response response =
                given()
                        .body(payload)
                        .put(PetEndpoint.UPDATE_PET);

        AssertionsHelper.assert_status_code(response, 500);
    }

    // Negatif test: olmayan peti silme
    @Test(priority = 8)
    public void delete_non_existing_pet_test() {


        Response response =
                given()
                        .pathParam("petId", 9543643)
                        .delete(PetEndpoint.DELETE_PET);

        AssertionsHelper.assert_status_code(response, 404);
    }
}
