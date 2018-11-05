package factories;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import interfaces.IBlock;
import interfaces.IBlockFactory;
import interfaces.IStampedDataFactory;
import interfaces.ITimestampedData;
import models.AnotherBlock;
import models.Block;

public class BlockFactory<T> implements IBlockFactory<Block<T>>{
	
	private IStampedDataFactory<T> factory;
	private IBidirectionalCipher dataCipher;
	
	public BlockFactory( IStampedDataFactory<T> factory) {
		this.setFactory(factory);
	}

	@Override
	public Block<T> createFromBlock(IBlock block) throws ParseException{		
		JSONParser	parser = new JSONParser();
		JSONObject	jsonObject = (JSONObject) parser.parse( this.getDataCipher().decrypt( block.blockHash() ) );
		
		return new Block<T>( block, getFactory().createStampedData( jsonObject ) );						
	}
	
	public IBlock createBlockToPersist(String previousHash, ITimestampedData<T> stampedData) {		
		return new AnotherBlock( previousHash, this.getDataCipher().encrypt( toJSON(stampedData).toJSONString() ) );
	}

	private JSONObject toJSON(ITimestampedData<T> stampedData) {
		JSONObject	jsonObject =  new JSONObject();
		
		getFactory().toJSON( stampedData, jsonObject );
		
		return jsonObject;
	}

	private IStampedDataFactory<T> getFactory() {
		return factory;
	}

	private void setFactory(IStampedDataFactory<T> factory) {
		this.factory = factory;
	}

	public IBidirectionalCipher getDataCipher() {
		return dataCipher;
	}

	public void setDataCipher(IBidirectionalCipher dataCipher) {
		this.dataCipher = dataCipher;
	}
	
}
