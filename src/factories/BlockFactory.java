package factories;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import hashgenerator.IHashGenerator;
import interfaces.IBlock;
import interfaces.IBlockData;
import interfaces.IBlockFactory;
import interfaces.IStampedDataFactory;
import interfaces.ITimestampedData;
import models.BlockStoreable;
import models.BlockRecuperable;

public class BlockFactory<T, R> implements IBlockFactory<IBlockData<R>>{
	
	private IStampedDataFactory<T,R> factory;
	private IBidirectionalCipher dataCipher;
	
	public BlockFactory( IStampedDataFactory<T,R> factory) {
		this.setFactory(factory);
	}

	@Override
	public IBlockData<R> createFromBlock(IBlock block) throws ParseException{		
		JSONParser	parser = new JSONParser();
		JSONObject	jsonObject = (JSONObject) parser.parse( this.getDataCipher().decrypt( block.blockHash() ) );
		
		return new BlockRecuperable<R>( block, getFactory().createStampedData( jsonObject ) );						
	}
	
	public IBlock createBlockToPersist(String previousHash, ITimestampedData<T> stampedData) {		
		return new BlockStoreable( previousHash, this.getDataCipher().encrypt( toJSON(stampedData).toJSONString() ) );
	}

	private JSONObject toJSON(ITimestampedData<T> stampedData) {
		JSONObject	jsonObject =  new JSONObject();
		
		getFactory().toJSON( stampedData, jsonObject );
		
		return jsonObject;
	}

	private IStampedDataFactory<T,R> getFactory() {
		return factory;
	}

	private void setFactory(IStampedDataFactory<T,R> factory) {
		this.factory = factory;
	}

	public IBidirectionalCipher getDataCipher() {
		return dataCipher;
	}

	public void setDataCipher(IBidirectionalCipher dataCipher) {
		this.dataCipher = dataCipher;
	}

	public void setGeneradorHash(IHashGenerator<T> generadorHash) {
		this.getFactory().setGeneradorHash( generadorHash );
		
	}
	
}
