package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Expense type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Expenses", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Expense implements Model {
  public static final QueryField ID = field("Expense", "id");
  public static final QueryField TRIP = field("Expense", "tripID");
  public static final QueryField DESCRIPTION = field("Expense", "description");
  public static final QueryField AMOUNT = field("Expense", "amount");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Trip") @BelongsTo(targetName = "tripID", targetNames = {"tripID"}, type = Trip.class) Trip trip;
  private final @ModelField(targetType="String", isRequired = true) String description;
  private final @ModelField(targetType="Float", isRequired = true) Double amount;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Trip getTrip() {
      return trip;
  }
  
  public String getDescription() {
      return description;
  }
  
  public Double getAmount() {
      return amount;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Expense(String id, Trip trip, String description, Double amount) {
    this.id = id;
    this.trip = trip;
    this.description = description;
    this.amount = amount;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Expense expense = (Expense) obj;
      return ObjectsCompat.equals(getId(), expense.getId()) &&
              ObjectsCompat.equals(getTrip(), expense.getTrip()) &&
              ObjectsCompat.equals(getDescription(), expense.getDescription()) &&
              ObjectsCompat.equals(getAmount(), expense.getAmount()) &&
              ObjectsCompat.equals(getCreatedAt(), expense.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), expense.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTrip())
      .append(getDescription())
      .append(getAmount())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Expense {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("trip=" + String.valueOf(getTrip()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("amount=" + String.valueOf(getAmount()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DescriptionStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Expense justId(String id) {
    return new Expense(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      trip,
      description,
      amount);
  }
  public interface DescriptionStep {
    AmountStep description(String description);
  }
  

  public interface AmountStep {
    BuildStep amount(Double amount);
  }
  

  public interface BuildStep {
    Expense build();
    BuildStep id(String id);
    BuildStep trip(Trip trip);
  }
  

  public static class Builder implements DescriptionStep, AmountStep, BuildStep {
    private String id;
    private String description;
    private Double amount;
    private Trip trip;
    @Override
     public Expense build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Expense(
          id,
          trip,
          description,
          amount);
    }
    
    @Override
     public AmountStep description(String description) {
        Objects.requireNonNull(description);
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep amount(Double amount) {
        Objects.requireNonNull(amount);
        this.amount = amount;
        return this;
    }
    
    @Override
     public BuildStep trip(Trip trip) {
        this.trip = trip;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Trip trip, String description, Double amount) {
      super.id(id);
      super.description(description)
        .amount(amount)
        .trip(trip);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder amount(Double amount) {
      return (CopyOfBuilder) super.amount(amount);
    }
    
    @Override
     public CopyOfBuilder trip(Trip trip) {
      return (CopyOfBuilder) super.trip(trip);
    }
  }
  

  public static class ExpenseIdentifier extends ModelIdentifier<Expense> {
    private static final long serialVersionUID = 1L;
    public ExpenseIdentifier(String id) {
      super(id);
    }
  }
  
}
