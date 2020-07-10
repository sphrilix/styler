/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

public class ParticipantReader extends Reader<Participant> {
    private final String pathRoomSid;
    private Participant.Status status;
    private String identity;
    private DateTime dateCreatedAfter;
    private DateTime dateCreatedBefore;

    /**
     * Construct a new ParticipantReader.
     *
     * @param pathRoomSid The SID of the room with the Participant resources to read
     */
    public ParticipantReader(final String pathRoomSid) {
        this.pathRoomSid = pathRoomSid;
    }

    /**
     * Read only the participants with this status. Can be: `connected` or
     * `disconnected`. For `in-progress` Rooms the default Status is `connected`,
     * for `completed` Rooms only `disconnected` Participants are returned..
     *
     * @param status Read only the participants with this status
     * @return this
     */
    public ParticipantReader setStatus(final Participant.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Read only the Participants with this
     * [User](https://www.twilio.com/docs/chat/rest/user-resource) `identity`
     * value..
     *
     * @param identity Read only the Participants with this user identity value
     * @return this
     */
    public ParticipantReader setIdentity(final String identity) {
        this.identity = identity;
        return this;
    }

    /**
     * Read only Participants that started after this date in [ISO
     * 8601](https://en.wikipedia.org/wiki/ISO_8601#UTC) format..
     *
     * @param dateCreatedAfter Read only Participants that started after this date
     *                         in UTC ISO 8601 format
     * @return this
     */
    public ParticipantReader setDateCreatedAfter(final DateTime dateCreatedAfter) {
        this.dateCreatedAfter = dateCreatedAfter;
        return this;
    }

    /**
     * Read only Participants that started before this date in [ISO
     * 8601](https://en.wikipedia.org/wiki/ISO_8601#UTC) format..
     *
     * @param dateCreatedBefore Read only Participants that started before this
     *                          date in ISO 8601 format
     * @return this
     */
    public ParticipantReader setDateCreatedBefore(final DateTime dateCreatedBefore) {
        this.dateCreatedBefore = dateCreatedBefore;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Participant ResourceSet
     */
    @Override
    public ResourceSet<Participant> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Participant ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Participant> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VIDEO.toString(),
            "/v1/Rooms/" + this.pathRoomSid + "/Participants",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Participant ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Participant> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Participant> nextPage(final Page<Participant> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.VIDEO.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Participant> previousPage(final Page<Participant> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.VIDEO.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Participant Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Participant> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "participants",
            response.getContent(),
            Participant.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (identity != null) {
            request.addQueryParam("Identity", identity);
        }

        if (dateCreatedAfter != null) {
            request.addQueryParam("DateCreatedAfter", dateCreatedAfter.toString());
        }

        if (dateCreatedBefore != null) {
            request.addQueryParam("DateCreatedBefore", dateCreatedBefore.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}