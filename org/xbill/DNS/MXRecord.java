// Copyright (c) 1999 Brian Wellington (bwelling@xbill.org)
// Portions Copyright (c) 1999 Network Associates, Inc.

import java.io.*;
import java.util.*;

public class dnsMXRecord extends dnsRecord {

short priority;
dnsName target;

public
dnsMXRecord(dnsName _name, short _dclass, int _ttl, int _priority,
	    dnsName _target)
{
	super(_name, dns.MX, _dclass, _ttl);
	priority = (short) _priority;
	target = _target;
}

public
dnsMXRecord(dnsName _name, short _dclass, int _ttl,
	    int length, CountedDataInputStream in, dnsCompression c)
throws IOException
{
	super(_name, dns.MX, _dclass, _ttl);
	if (in == null)
		return;
	priority = (short) in.readUnsignedShort();
	target = new dnsName(in, c);
}

public
dnsMXRecord(dnsName _name, short _dclass, int _ttl, MyStringTokenizer st,
	    dnsName origin)
throws IOException
{
	super(_name, dns.MX, _dclass, _ttl);
	priority = Short.parseShort(st.nextToken());
	target = new dnsName(st.nextToken(), origin);
}

public String
toString() {
	StringBuffer sb = toStringNoData();
	if (target != null) {
		sb.append(priority);
		sb.append(" ");
		sb.append(target);
	}
	return sb.toString();
}

public dnsName
getTarget() {
	return target;
}

public short
getPriority() {
	return priority;
}

byte []
rrToWire(dnsCompression c) throws IOException {
	if (target == null)
		return null;

	ByteArrayOutputStream bs = new ByteArrayOutputStream();
	CountedDataOutputStream ds = new CountedDataOutputStream(bs);

	ds.writeShort(priority);
	target.toWire(ds, null);
	return bs.toByteArray();
}

}
