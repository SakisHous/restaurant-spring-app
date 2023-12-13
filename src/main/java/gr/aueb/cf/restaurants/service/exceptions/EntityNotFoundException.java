package gr.aueb.cf.restaurants.service.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class<?> clazz, Long id) {
        super("Entity with name " + clazz.getSimpleName() + " with id: " + id + " not found");
    }
}
