// Copyright (c) 1999-2004 Brian Wellington (bwelling@xbill.org)

package org.xbill.DNS;

import java.io.*;
import java.text.*;
import java.util.*;
import org.xbill.DNS.utils.*;

/**
 * Recource Record Signature - An RRSIG provides the digital signature of an
 * RRset, so that the data can be authenticated by a DNSSEC-capable resolver.
 * The signature is generated by a key contained in a DNSKEY Record.
 * @see RRset
 * @see DNSSEC
 * @see KEYRecord
 *
 * @author Brian Wellington
 */

public class RRSIGRecord extends SIGBase {

private static RRSIGRecord member = new RRSIGRecord();

private
RRSIGRecord() {}

private
RRSIGRecord(Name name, int dclass, long ttl) {
	super(name, Type.RRSIG, dclass, ttl);
}

static RRSIGRecord
getMember() {
	return member;
}

/**
 * Creates an SIG Record from the given data
 * @param covered The RRset type covered by this signature
 * @param alg The cryptographic algorithm of the key that generated the
 * signature
 * @param origttl The original TTL of the RRset
 * @param expire The time at which the signature expires
 * @param timeSigned The time at which this signature was generated
 * @param footprint The footprint/key id of the signing key.
 * @param signer The owner of the signing key
 * @param signature Binary data representing the signature
 */
public
RRSIGRecord(Name name, int dclass, long ttl, int covered, int alg, long origttl,
	    Date expire, Date timeSigned, int footprint, Name signer,
	    byte [] signature)
{
	super(name, Type.RRSIG, dclass, ttl, covered, alg, origttl, expire,
	      timeSigned, footprint, signer, signature);
}

Record
rrFromWire(Name name, int type, int dclass, long ttl, int length,
	   DataByteInputStream in)
throws IOException
{
	return rrFromWire(new RRSIGRecord(name, dclass, ttl), length, in);
}

Record
rdataFromString(Name name, int dclass, long ttl, Tokenizer st, Name origin)
throws IOException
{
	return rdataFromString(new RRSIGRecord(name, dclass, ttl), st, origin);
}

}
