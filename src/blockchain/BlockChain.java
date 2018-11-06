package blockchain;

import java.util.Collection;

import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import factories.BlockFactory;
import factories.HashedDataFactory;
import factories.StampedDataFactory;
import hashgenerator.HashGenerator;
import hashgenerator.IHashGenerator;
import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.data.IDataFactory;
import interfaces.hasheddata.IHashedData;
import interfaces.timestamp.ITimestampedData;
import repositories.BlockRepository;
import tsproviders.ITimestampProvider;

public class BlockChain<T, R> implements IBlockChain<T, R> {
	private BlockFactory<T, R> blockFactory;
	private HashGenerator<T> hashGenerator;
	private ITimestampProvider<T> timestampingProvider;
	private BlockRepository<IBlock> blockRepository;

	public BlockChain(IDataFactory<T, R> dataFactory) {
		this.setBlockRepository(new BlockRepository<IBlock>());
		this.createBlockFactory(dataFactory);		
	}

	private void createBlockFactory(IDataFactory<T, R> dataFactory) {
		this.setBlockFactory(new BlockFactory<T, R>(new StampedDataFactory<T, R>(new HashedDataFactory<T, R>(dataFactory))));
	}

	@Override
	public void add(T data) {
		this.getBlockRepository().add(createBlockToPersist(data));
	}

	private IBlock createBlockToPersist(T data) {
		return this.getBlockFactory().createBlockToPersist(this.getLastHash(), createTimestampedData(data));
	}

	@Override
	public void getAll(Collection<IBlockData<R>> bloques) {
		try {
			this.getBlockRepository().getAll(bloques, this.getBlockFactory());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public IHashGenerator<T> getGeneradorHash() {
		return hashGenerator;
	}

	public void setGeneradorHash(HashGenerator<T> generadorHash) {
		this.hashGenerator = generadorHash;

		this.getBlockFactory().setHashValidator(generadorHash);
	}

	public ITimestampProvider<T> getTimestampingProvider() {
		return timestampingProvider;
	}

	public void setTimestampingProvider(ITimestampProvider<T> timestampingProvider) {
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

	private BlockRepository<IBlock> getBlockRepository() {
		return blockRepository;
	}

	private void setBlockRepository(BlockRepository<IBlock> repository) {
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
