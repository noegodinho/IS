
package artifact;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the artifact package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListSubscriptions_QNAME = new QName("http://soap/", "listSubscriptions");
    private final static QName _AddSubscription_QNAME = new QName("http://soap/", "addSubscription");
    private final static QName _ListSubscriptionsResponse_QNAME = new QName("http://soap/", "listSubscriptionsResponse");
    private final static QName _AddSubscriptionResponse_QNAME = new QName("http://soap/", "addSubscriptionResponse");
    private final static QName _RemoveSubscriptionResponse_QNAME = new QName("http://soap/", "removeSubscriptionResponse");
    private final static QName _RemoveSubscription_QNAME = new QName("http://soap/", "removeSubscription");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: artifact
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemoveSubscriptionResponse }
     * 
     */
    public RemoveSubscriptionResponse createRemoveSubscriptionResponse() {
        return new RemoveSubscriptionResponse();
    }

    /**
     * Create an instance of {@link AddSubscriptionResponse }
     * 
     */
    public AddSubscriptionResponse createAddSubscriptionResponse() {
        return new AddSubscriptionResponse();
    }

    /**
     * Create an instance of {@link RemoveSubscription }
     * 
     */
    public RemoveSubscription createRemoveSubscription() {
        return new RemoveSubscription();
    }

    /**
     * Create an instance of {@link AddSubscription }
     * 
     */
    public AddSubscription createAddSubscription() {
        return new AddSubscription();
    }

    /**
     * Create an instance of {@link ListSubscriptions }
     * 
     */
    public ListSubscriptions createListSubscriptions() {
        return new ListSubscriptions();
    }

    /**
     * Create an instance of {@link ListSubscriptionsResponse }
     * 
     */
    public ListSubscriptionsResponse createListSubscriptionsResponse() {
        return new ListSubscriptionsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListSubscriptions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "listSubscriptions")
    public JAXBElement<ListSubscriptions> createListSubscriptions(ListSubscriptions value) {
        return new JAXBElement<ListSubscriptions>(_ListSubscriptions_QNAME, ListSubscriptions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddSubscription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addSubscription")
    public JAXBElement<AddSubscription> createAddSubscription(AddSubscription value) {
        return new JAXBElement<AddSubscription>(_AddSubscription_QNAME, AddSubscription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListSubscriptionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "listSubscriptionsResponse")
    public JAXBElement<ListSubscriptionsResponse> createListSubscriptionsResponse(ListSubscriptionsResponse value) {
        return new JAXBElement<ListSubscriptionsResponse>(_ListSubscriptionsResponse_QNAME, ListSubscriptionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddSubscriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addSubscriptionResponse")
    public JAXBElement<AddSubscriptionResponse> createAddSubscriptionResponse(AddSubscriptionResponse value) {
        return new JAXBElement<AddSubscriptionResponse>(_AddSubscriptionResponse_QNAME, AddSubscriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSubscriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "removeSubscriptionResponse")
    public JAXBElement<RemoveSubscriptionResponse> createRemoveSubscriptionResponse(RemoveSubscriptionResponse value) {
        return new JAXBElement<RemoveSubscriptionResponse>(_RemoveSubscriptionResponse_QNAME, RemoveSubscriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSubscription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "removeSubscription")
    public JAXBElement<RemoveSubscription> createRemoveSubscription(RemoveSubscription value) {
        return new JAXBElement<RemoveSubscription>(_RemoveSubscription_QNAME, RemoveSubscription.class, null, value);
    }

}
