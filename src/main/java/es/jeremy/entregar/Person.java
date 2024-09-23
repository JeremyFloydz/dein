package es.jeremy.entregar;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa una persona.
 */
public class Person {
    /** Contador para generar IDs únicos. */
    private static AtomicInteger personSequence = new AtomicInteger(0);
    private int personId; // ID de la persona
    private String firstName; // Nombre de la persona
    private String lastName; // Apellido de la persona
    private LocalDate birthDate; // Fecha de nacimiento

    /**
     * Constructor para crear una nueva persona.
     *
     * @param firstName Nombre de la persona
     * @param lastName Apellido de la persona
     * @param birthDate Fecha de nacimiento de la persona
     */
    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.personId = personSequence.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    /**
     * Obtiene el ID de la persona.
     * @return El ID de la persona.
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Establece el ID de la persona.
     * @param personId El nuevo ID de la persona.
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Obtiene el primer nombre de la persona.
     * @return El primer nombre.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el primer nombre de la persona.
     * @param firstName El nuevo primer nombre.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido de la persona.
     * @return El apellido.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido de la persona.
     * @param lastName El nuevo apellido.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene la fecha de nacimiento de la persona.
     * @return La fecha de nacimiento.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Establece la fecha de nacimiento de la persona.
     * @param birthDate La nueva fecha de nacimiento.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

