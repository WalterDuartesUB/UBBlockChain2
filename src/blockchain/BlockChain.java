package blockchain;

import java.util.Collection;

import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import factories.BlockFactory;
import factories.HashedDataFactory;
import factories.TimestampedDataFactory;
import hashgenerator.HashGenerator;
import hashgenerator.IHashGenerator;
import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.block.IBlockDataFactory;
import interfaces.block.IBlockFactory;
import interfaces.data.IDataFactory;
import interfaces.data.IDataHasheable;
import interfaces.data.IDataRecuperable;
import interfaces.filter.DataFilterAcceptAll;
import interfaces.filter.IDataFilter;
import interfaces.hasheddata.IHashedData;
import interfaces.repositories.IBlockRepository;
import interfaces.timestamp.ITimestampedData;
import interfaces.tsproviders.ITimestampProvider;
import repositories.BlockRepository;

public class BlockChain<T extends IDataHasheable, R extends IDataRecuperable> implements IBlockChain<T, R> {
	private BlockFactory<T, R> blockFactory;
	private HashGenerator hashGenerator;
	private ITimestampProvider timestampingProvider;
	private IBlockRepository<IBlock> blockRepository;

	public BlockChain(IDataFactory<T, R> dataFactory) {
		this.setBlockRepository(new BlockRepository<IBlock>());
		this.createBlockFactory(dataFactory);		
	}

	private void createBlockFactory(IDataFactory<T, R> dataFactory) {
		this.setBlockFactory(new BlockFactory<T, R>(new TimestampedDataFactory<T, R>(new HashedDataFactory<T, R>(dataFactory))));
	}

	@Override
	public void add(T data) {
		this.getBlockRepository().add(createBlockToPersist(data));
	}

	private IBlock createBlockToPersist(T data) {
		return this.blockFactory().createBlockToPersist(this.getLastHash(), createTimestampedData(data));
	}

	@Override
	public void getAll(Collection<IBlockData<R>> bloques) {		
		this.getAll(bloques, this.blockDataFactory(), new DataFilterAcceptAll<R>());		
	}
	
	@Override
	public <R2> void getAll(Collection<IBlockData<R2>> bloques, IDataFactory<T, R2> dataFactory) {
		this.getAll(bloques, dataFactory, new DataFilterAcceptAll<R2>() );
	}
	
	@Override
	public <R2> void getAll(Collection<IBlockData<R2>> bloques, IDataFactory<T, R2> factory, IDataFilter<R2> filter) {		
		this.getAll(bloques, new BlockFactory<T, R2>(new TimestampedDataFactory<T, R2>(new HashedDataFactory<T, R2>(factory, this.hashGenerator)), this.getBlockFactory().getDataCipher()), filter);		
	}	
	
	private <R2>void  getAll(Collection<IBlockData<R2>> bloques, IBlockDataFactory<R2> blockFactory, IDataFilter<R2> filter){
		try {
			this.getBlockRepository().getAll(bloques, blockFactory, filter);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	private IBlockFactory<T> blockFactory() {
		return this.getBlockFactory();
	}
	
	private IBlockDataFactory<R> blockDataFactory() {
		return this.getBlockFactory();
	}

	public IHashGenerator getGeneradorHash() {
		return hashGenerator;
	}

	public void setGeneradorHash(HashGenerator generadorHash) {
		this.hashGenerator = generadorHash;

		this.getBlockFactory().setHashValidator(generadorHash);
	}

	public ITimestampProvider getTimestampingProvider() {
		return timestampingProvider;
	}

	public void setTimestampingProvider(ITimestampProvider timestampingProvider) {
		this.timestampingProvider = timestampingProvider;
	}

	public void setDataCipher(IBidirectionalCipher dataCipher) {
		this.getBlockFactory().setDataCipher(dataCipher);
	}

	private BlockFactory<T, R> getBlockFactory() {
		return blockFactory;
	}

	private void setBlockFactory(BlockFactory<T, R> blockFactory) {
		this.blockFactory = blockFactory;
	}

	private IBlockRepository<IBlock> getBlockRepository() {
		return blockRepository;
	}

	private void setBlockRepository(IBlockRepository<IBlock> repository) {
		this.blockRepository = repository;
	}

	private ITimestampedData<T> createTimestampedData(T data) {
		return this.getTimestampingProvider().stamp(this.createHashedData(data));
	}

	private IHashedData<T> createHashedData(T hashedData) {
		return this.getGeneradorHash().hash(hashedData);
	}

	private String getLastHash() {
		return this.getBlockRepository().getLastBlockHash();
	}
}
