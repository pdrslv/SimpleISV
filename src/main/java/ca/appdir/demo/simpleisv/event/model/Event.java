package ca.appdir.demo.simpleisv.event.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private EventType type;
    private Martketplace martketplace;
    private String applicationUuid;
    private Flag flag;
    private String returnUrl;
    private User creator;
    private Payload payload;

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Martketplace {
        private String partner;
        private String baseUrl;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String email;
        private String firstName;
        private String lastName;
        private String language;
        private String locale;
        private String openId;
        private String uuid;
        private Address address;
        private Map<String, String> attributes;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        private String city;
        private String country;
        private String state;
        private String street1;
        private String street2;
        private String zip;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        private Account account;
        private Company company;
        private Order order;
        private AddonInstance addonInstance;
        private AddonInstance addonBinding;
        private Notice notice;
        private Map<String, String> configuration;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Account {
        private String accountIdentifier;
        private AccountStatus status;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Company {
        private String uuid;
        private String externalId;
        private String name;
        private String email;
        private String phoneNumber;
        private String website;
        private String country;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order {
        private String editionCode;
        private String addonOfferingCode;
        private String pricingDuration;
        private List<Item> items;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String unit;
        private int quantity;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AddonInstance {
        private String id;
    }

    @Data
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Notice {
        private NoticeType type;
        private String message;
    }

    public enum EventType {
        SUBSCRIPTION_ORDER,
        SUBSCRIPTION_CHANGE,
        SUBSCRIPTION_CANCEL,
        SUBSCRIPTION_NOTICE,
        USER_ASSIGNMENT,
        USER_UNASSIGNMENT,
        USER_UPDATED;
    }

    enum Flag {
        STATELESS,
        DEVELOPMENT;
    }

    enum AccountStatus {
        INITIALIZED,
        FAILED,
        FREE_TRIAL,
        FREE_TRIAL_EXPIRED,
        ACTIVE,
        SUSPENDED,
        CANCELLED;
    }

    enum NoticeType {
        REACTIVATED,
        DEACTIVATED,
        CLOSED;
    }
}


