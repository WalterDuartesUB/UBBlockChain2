package factories;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import hashgenerator.IHashValidator;
import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.block.IBlockFactory;
import interfaces.timestamp.ITimestampedData;
import interfaces.timestamp.ITimestampedDataFactory;
import models.block.BlockRecuperable;
import models.block.BlockStoreable;

public class BlockFactory<S, R> implements IBlockFactory<S,R>{
	
	private ITimestampedDataFactory<S,R> factory;
	private IBidirectionalCipher dataCipher;
	
	public BlockFactory( ITimestampedDataFactory<S,R> factory) {
		this.setFactory(factory);
	}

	@Override
	public IBlockData<R> createFromBlock(IBlock block) throws ParseException{		
		JSONParser	parser = new JSONParser();
		JSONObject	jsonObject = (JSONObject) parser.parse( this.getDataCipher().decrypt( block.blockHash() ) );
		
		return new BlockRecuperable<R>( block, getFactory().createStampedData( jsonObject ) );						
	}
	
	@Override
	public IBlock createBlockToPersist(String previousHash, ITimestampedData<S> stampedData) {		
		return new BlockStoreable( previousHash, this.getDataCipher().encrypt( toJSON(stampedData).toJSONString() ) );
	}

	private JSONObject toJSON(ITimestampedData<S> stampedData) {
		JSONObject	jsonObject =  new JSONObject();
		
		getFactory().toJSON( stampedData, jsonObject );
		
		return jsonObject;
	}

	private ITimestampedDataFactory<S,R> getFactory() {
		return factory;
	}

	private void setFactory(ITimestampedDataFactory<S,R> factory) {
		this.factory = factory;
	}

	public IBidirectionalCipher getDataCipher() {
		return dataCipher;
	}

	public void setDataCipher(IBidirectionalCipher dataCipher) {
		this.dataCipher = dataCipher;
	}

	public void setHashValidator(IHashValidator hashValidator) {
		this.getFactory().setHashValidator( hashValidator );
		
	}
	
}
