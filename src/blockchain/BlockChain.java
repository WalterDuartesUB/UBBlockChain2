package blockchain;

import java.util.Collection;

import org.json.simple.parser.ParseException;

import cipher.IBidirectionalCipher;
import factories.BlockFactory;
import factories.HashedDataFactory;
import factories.StampedDataFactory;
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
	private IHashGenerator<T> generadorHash;
	private ITimestampProvider<T> timestampingProvider;
	private BlockRepository<IBlock> repositorio;

	public BlockChain(IDataFactory<T, R> dataFactory) {
		this.setBlockFactory(new BlockFactory<T, R>(new StampedDataFactory<T, R>(new HashedDataFactory<T, R>(dataFactory))));
		this.setRepositorio(new BlockRepository<IBlock>());
	}

	@Override
	public void add(T data) {
		this.getRepositorio().add(createBlockToPersist(data));
	}

	private IBlock createBlockToPersist(T data) {
		return this.getBlockFactory().createBlockToPersist(this.getLastHash(), createTimestampedData(data));
	}

	@Override
	public void getAll(Collection<IBlockData<R>> bloques) {
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
		this.getBlockFactory().setDataCipher(dataCipher);
	}

	private BlockFactory<T, R> getBlockFactory() {
		return blockFactory;
	}

	private void setBlockFactory(BlockFactory<T, R> blockFactory) {
		this.blockFactory = blockFactory;
	}

	private BlockRepository<IBlock> getRepositorio() {
		return repositorio;
	}

	private void setRepositorio(BlockRepository<IBlock> repositorio) {
		this.repositorio = repositorio;
	}

	private ITimestampedData<T> createTimestampedData(T data) {
		return this.getTimestampingProvider().stamp(this.createHashedData(data));
	}

	private IHashedData<T> createHashedData(T hashedData) {
		return this.getGeneradorHash().hash(hashedData);
	}

	private String getLastHash() {
		return this.getRepositorio().getLastBlockHash();
	}
}
