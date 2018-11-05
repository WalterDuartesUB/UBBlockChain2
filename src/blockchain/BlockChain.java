package blockchain;
import java.util.Collection;

import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import factories.BlockFactory;
import factories.HashedDataFactory;
import factories.StampedDataFactory;
import hashgenerator.IHashGenerator;
import interfaces.IBlock;
import interfaces.IDataFactory;
import interfaces.IHashedData;
import interfaces.ITimestampedData;
import models.Block;
import repositories.BlockRepository;
import tsproviders.ITimestampProvider;

public class BlockChain<T> implements IBlockChain<T> {
	private BlockFactory<T> 			blockFactory;
	private BlockRepository<IBlock> 	repositorio;
	private IHashGenerator<T> 			generadorHash;
	private ITimestampProvider<T>   	timestampingProvider;
	
	public BlockChain(IDataFactory<T> dataFactory) {
		this.setBlockFactory(new BlockFactory<T>(new StampedDataFactory<T>(new HashedDataFactory<T>(dataFactory))));
		this.setRepositorio(new BlockRepository<IBlock>());
	}

	private BlockFactory<T> getBlockFactory() {
		return blockFactory;
	}

	private void setBlockFactory(BlockFactory<T> blockFactory) {
		this.blockFactory = blockFactory;
	}

	private BlockRepository<IBlock> getRepositorio() {
		return repositorio;
	}

	private void setRepositorio(BlockRepository<IBlock> repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void add(T data) {
		this.getRepositorio().add(this.getBlockFactory().createBlockToPersist(getLastHash(), createTimestampedData(data)));
	}

	private ITimestampedData<T> createTimestampedData(T data) {
		return this.getTimestampingProvider().stamp( this.createHashedData(data) );
	}

	private IHashedData<T> createHashedData(T hashedData) {		
		return this.getGeneradorHash().hash(hashedData);
	}

	private String getLastHash() {
		return this.getRepositorio().getLastBlockHash();
	}

	@Override
	public void getAll(Collection<Block<T>> bloques){
		try {
			this.getRepositorio().getAll(bloques, this.getBlockFactory());
		} catch (ParseException e) {		
			e.printStackTrace();
		}
	}

	public IHashGenerator<T> getGeneradorHash() {
		return generadorHash;
	}

	public void setGeneradorHash(IHashGenerator<T> generadorHash) {
		this.generadorHash = generadorHash;
		
		this.getBlockFactory().setGeneradorHash(generadorHash);
	}

	public ITimestampProvider<T> getTimestampingProvider() {
		return timestampingProvider;
	}

	public void setTimestampingProvider(ITimestampProvider<T> timestampingProvider) {
		this.timestampingProvider = timestampingProvider;
	}

	public void setDataCipher(IBidirectionalCipher dataCipher) {
		this.getBlockFactory().setDataCipher( dataCipher );
	}
}
