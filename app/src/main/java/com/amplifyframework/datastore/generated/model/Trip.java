package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Trip type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Trips", type = Model.Type.USER, version = 1)
public final class Trip implements Model {
  public static final QueryField ID = field("Trip", "id");
  public static final QueryField USER_ID = field("Trip", "userID");
  public static final QueryField NAME = field("Trip", "name");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Expense") @HasMany(associatedWith = "trip", type = Expense.class) List<Expense> expenses = null;
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
  
  public String getUserId() {
      return userID;
  }
  
  public String getName() {
      return name;
  }
  
  public List<Expense> getExpenses() {
      return expenses;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Trip(String id, String userID, String name) {
    this.id = id;
    this.userID = userID;
    this.name = name;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Trip trip = (Trip) obj;
      return ObjectsCompat.equals(getId(), trip.getId()) &&
              ObjectsCompat.equals(getUserId(), trip.getUserId()) &&
              ObjectsCompat.equals(getName(), trip.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), trip.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), trip.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Trip {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UserIdStep builder() {
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
  public static Trip justId(String id) {
    return new Trip(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userID,
      name);
  }
  public interface UserIdStep {
    NameStep userId(String userId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Trip build();
    BuildStep id(String id);
  }
  

  public static class Builder implements UserIdStep, NameStep, BuildStep {
    private String id;
    private String userID;
    private String name;
    @Override
     public Trip build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Trip(
          id,
          userID,
          name);
    }
    
    @Override
     public NameStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
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
    private CopyOfBuilder(String id, String userId, String name) {
      super.id(id);
      super.userId(userId)
        .name(name);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
  }
  

  public static class TripIdentifier extends ModelIdentifier<Trip> {
    private static final long serialVersionUID = 1L;
    public TripIdentifier(String id) {
      super(id);
    }
  }
  
}
