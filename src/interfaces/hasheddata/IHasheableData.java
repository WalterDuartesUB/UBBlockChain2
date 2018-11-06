package interfaces.hasheddata;

import java.io.IOException;

public interface IHasheableData {
	public byte[] toBytes() throws IOException;
}
