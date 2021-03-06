/**
 * This class file was automatically generated by jASN1 v1.8.2-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.pedefinitions;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class KeyObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class KeyCompontents implements Serializable {

		private static final long serialVersionUID = 1L;

		public static class SEQUENCE implements Serializable {

			private static final long serialVersionUID = 1L;

			public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

			public byte[] code = null;
			public BerOctetString keyType = null;
			public BerOctetString keyData = null;
			public UInt8 macLength = null;
			
			public SEQUENCE() {
			}

			public SEQUENCE(byte[] code) {
				this.code = code;
			}

			public SEQUENCE(BerOctetString keyType, BerOctetString keyData, UInt8 macLength) {
				this.keyType = keyType;
				this.keyData = keyData;
				this.macLength = macLength;
			}

			public int encode(BerByteArrayOutputStream os) throws IOException {
				return encode(os, true);
			}

			public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

				if (code != null) {
					for (int i = code.length - 1; i >= 0; i--) {
						os.write(code[i]);
					}
					if (withTag) {
						return tag.encode(os) + code.length;
					}
					return code.length;
				}

				int codeLength = 0;
				if (macLength != null) {
					codeLength += macLength.encode(os, false);
					// write tag: CONTEXT_CLASS, PRIMITIVE, 7
					os.write(0x87);
					codeLength += 1;
				}
				
				codeLength += keyData.encode(os, false);
				// write tag: CONTEXT_CLASS, PRIMITIVE, 6
				os.write(0x86);
				codeLength += 1;
				
				codeLength += keyType.encode(os, false);
				// write tag: CONTEXT_CLASS, PRIMITIVE, 0
				os.write(0x80);
				codeLength += 1;
				
				codeLength += BerLength.encodeLength(os, codeLength);

				if (withTag) {
					codeLength += tag.encode(os);
				}

				return codeLength;

			}

			public int decode(InputStream is) throws IOException {
				return decode(is, true);
			}

			public int decode(InputStream is, boolean withTag) throws IOException {
				int codeLength = 0;
				int subCodeLength = 0;
				BerTag berTag = new BerTag();

				if (withTag) {
					codeLength += tag.decodeAndCheck(is);
				}

				BerLength length = new BerLength();
				codeLength += length.decode(is);

				int totalLength = length.val;
				if (totalLength == -1) {
					subCodeLength += berTag.decode(is);

					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}
					if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
						keyType = new BerOctetString();
						subCodeLength += keyType.decode(is, false);
						subCodeLength += berTag.decode(is);
					}
					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}
					if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 6)) {
						keyData = new BerOctetString();
						subCodeLength += keyData.decode(is, false);
						subCodeLength += berTag.decode(is);
					}
					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}
					if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 7)) {
						macLength = new UInt8();
						subCodeLength += macLength.decode(is, false);
						subCodeLength += berTag.decode(is);
					}
					int nextByte = is.read();
					if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
					|| nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}

				codeLength += totalLength;

				subCodeLength += berTag.decode(is);
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
					keyType = new BerOctetString();
					subCodeLength += keyType.decode(is, false);
					subCodeLength += berTag.decode(is);
				}
				else {
					throw new IOException("Tag does not match the mandatory sequence element tag.");
				}
				
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 6)) {
					keyData = new BerOctetString();
					subCodeLength += keyData.decode(is, false);
					if (subCodeLength == totalLength) {
						return codeLength;
					}
					subCodeLength += berTag.decode(is);
				}
				else {
					throw new IOException("Tag does not match the mandatory sequence element tag.");
				}
				
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 7)) {
					macLength = new UInt8();
					subCodeLength += macLength.decode(is, false);
					if (subCodeLength == totalLength) {
						return codeLength;
					}
				}
				throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

				
			}

			public void encodeAndSave(int encodingSizeGuess) throws IOException {
				BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
				encode(os, false);
				code = os.getArray();
			}

			public String toString() {
				StringBuilder sb = new StringBuilder();
				appendAsString(sb, 0);
				return sb.toString();
			}

			public void appendAsString(StringBuilder sb, int indentLevel) {

				sb.append("{");
				sb.append("\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				if (keyType != null) {
					sb.append("keyType: ").append(keyType);
				}
				else {
					sb.append("keyType: <empty-required-field>");
				}
				
				sb.append(",\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				if (keyData != null) {
					sb.append("keyData: ").append(keyData);
				}
				else {
					sb.append("keyData: <empty-required-field>");
				}
				
				if (macLength != null) {
					sb.append(",\n");
					for (int i = 0; i < indentLevel + 1; i++) {
						sb.append("\t");
					}
					sb.append("macLength: ").append(macLength);
				}
				
				sb.append("\n");
				for (int i = 0; i < indentLevel; i++) {
					sb.append("\t");
				}
				sb.append("}");
			}

		}

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		public List<SEQUENCE> seqOf = null;

		public KeyCompontents() {
			seqOf = new ArrayList<SEQUENCE>();
		}

		public KeyCompontents(byte[] code) {
			this.code = code;
		}

		public KeyCompontents(List<SEQUENCE> seqOf) {
			this.seqOf = seqOf;
		}

		public int encode(BerByteArrayOutputStream os) throws IOException {
			return encode(os, true);
		}

		public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					os.write(code[i]);
				}
				if (withTag) {
					return tag.encode(os) + code.length;
				}
				return code.length;
			}

			int codeLength = 0;
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(os, true);
			}

			codeLength += BerLength.encodeLength(os, codeLength);

			if (withTag) {
				codeLength += tag.encode(os);
			}

			return codeLength;
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, true);
		}

		public int decode(InputStream is, boolean withTag) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			BerTag berTag = new BerTag();
			if (withTag) {
				codeLength += tag.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);
			int totalLength = length.val;

			if (length.val == -1) {
				while (true) {
					subCodeLength += berTag.decode(is);

					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}

					SEQUENCE element = new SEQUENCE();
					subCodeLength += element.decode(is, false);
					seqOf.add(element);
				}
			}
			while (subCodeLength < totalLength) {
				SEQUENCE element = new SEQUENCE();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = os.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<SEQUENCE> it = seqOf.iterator();
				if (it.hasNext()) {
					it.next().appendAsString(sb, indentLevel + 1);
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						it.next().appendAsString(sb, indentLevel + 1);
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	public BerOctetString keyUsageQualifier = null;
	public BerOctetString keyAccess = null;
	public BerOctetString keyIdentifier = null;
	public BerOctetString keyVersionNumber = null;
	public BerOctetString keyCounterValue = null;
	public KeyCompontents keyCompontents = null;
	
	public KeyObject() {
	}

	public KeyObject(byte[] code) {
		this.code = code;
	}

	public KeyObject(BerOctetString keyUsageQualifier, BerOctetString keyAccess, BerOctetString keyIdentifier, BerOctetString keyVersionNumber, BerOctetString keyCounterValue, KeyCompontents keyCompontents) {
		this.keyUsageQualifier = keyUsageQualifier;
		this.keyAccess = keyAccess;
		this.keyIdentifier = keyIdentifier;
		this.keyVersionNumber = keyVersionNumber;
		this.keyCounterValue = keyCounterValue;
		this.keyCompontents = keyCompontents;
	}

	public int encode(BerByteArrayOutputStream os) throws IOException {
		return encode(os, true);
	}

	public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			if (withTag) {
				return tag.encode(os) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		codeLength += keyCompontents.encode(os, true);
		
		if (keyCounterValue != null) {
			codeLength += keyCounterValue.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 5
			os.write(0x85);
			codeLength += 1;
		}
		
		codeLength += keyVersionNumber.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 3
		os.write(0x83);
		codeLength += 1;
		
		codeLength += keyIdentifier.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 2
		os.write(0x82);
		codeLength += 1;
		
		if (keyAccess != null) {
			codeLength += keyAccess.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 22
			os.write(0x96);
			codeLength += 1;
		}
		
		codeLength += keyUsageQualifier.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 21
		os.write(0x95);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(os, codeLength);

		if (withTag) {
			codeLength += tag.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		if (totalLength == -1) {
			subCodeLength += berTag.decode(is);

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 21)) {
				keyUsageQualifier = new BerOctetString();
				subCodeLength += keyUsageQualifier.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 22)) {
				keyAccess = new BerOctetString();
				subCodeLength += keyAccess.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
				keyIdentifier = new BerOctetString();
				subCodeLength += keyIdentifier.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
				keyVersionNumber = new BerOctetString();
				subCodeLength += keyVersionNumber.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 5)) {
				keyCounterValue = new BerOctetString();
				subCodeLength += keyCounterValue.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(KeyCompontents.tag)) {
				keyCompontents = new KeyCompontents();
				subCodeLength += keyCompontents.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			int nextByte = is.read();
			if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
			|| nextByte != 0) {
				if (nextByte == -1) {
					throw new EOFException("Unexpected end of input stream.");
				}
				throw new IOException("Decoded sequence has wrong end of contents octets");
			}
			codeLength += subCodeLength + 1;
			return codeLength;
		}

		codeLength += totalLength;

		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 21)) {
			keyUsageQualifier = new BerOctetString();
			subCodeLength += keyUsageQualifier.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 22)) {
			keyAccess = new BerOctetString();
			subCodeLength += keyAccess.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
			keyIdentifier = new BerOctetString();
			subCodeLength += keyIdentifier.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
			keyVersionNumber = new BerOctetString();
			subCodeLength += keyVersionNumber.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 5)) {
			keyCounterValue = new BerOctetString();
			subCodeLength += keyCounterValue.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(KeyCompontents.tag)) {
			keyCompontents = new KeyCompontents();
			subCodeLength += keyCompontents.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (keyUsageQualifier != null) {
			sb.append("keyUsageQualifier: ").append(keyUsageQualifier);
		}
		else {
			sb.append("keyUsageQualifier: <empty-required-field>");
		}
		
		if (keyAccess != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("keyAccess: ").append(keyAccess);
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (keyIdentifier != null) {
			sb.append("keyIdentifier: ").append(keyIdentifier);
		}
		else {
			sb.append("keyIdentifier: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (keyVersionNumber != null) {
			sb.append("keyVersionNumber: ").append(keyVersionNumber);
		}
		else {
			sb.append("keyVersionNumber: <empty-required-field>");
		}
		
		if (keyCounterValue != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("keyCounterValue: ").append(keyCounterValue);
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (keyCompontents != null) {
			sb.append("keyCompontents: ");
			keyCompontents.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("keyCompontents: <empty-required-field>");
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

