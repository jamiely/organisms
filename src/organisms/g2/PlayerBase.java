package organisms.g2;

import java.awt.Color;
import java.util.Random;

import organisms.Move;
import organisms.OrganismsGame;
import organisms.Player;
import organisms.g2.data.MoveInput;

public abstract class PlayerBase implements Player {
	private static final long serialVersionUID = -2855747689179359665L;

	private Random rand;
	private int state;
	private OrganismsGame game;
	private Color color;

	private String name;
	private MoveFactory moveFactory;
	
	public Memory memory;
	
	@Override
	public void register(OrganismsGame __amoeba, int key) throws Exception {
		setState(157);
		setGame(__amoeba);
		setColor(Color.GREEN);
		setRand(new Random());
		setMoveFactory(new MoveFactory(rand));
		setMemory(new Memory());
		memory.useParentMessage(key);
	}

	@Override
	public String name() throws Exception {
		return getName();
	}

	@Override
	public Color color() throws Exception {
		return getColor();
	}

	@Override
	public boolean interactive() throws Exception {
		return false;
	}

	@Override
	public Move move(boolean[] foodpresent, int[] neighbors, int foodleft, int energyleft)
			throws Exception {

		MoveInput input = MoveInput.createMoveInput(foodpresent, neighbors, foodleft, energyleft);
		getMemory().rememberInfo(input);
		
		Move move = move(input);
		getMemory().rememberMove(move);
		if(move.type() == REPRODUCE) {
			birth();
		}
		
		setAge(getAge() + 1);
		
		return move;
	}
	

	public abstract Move move(MoveInput input);

	@Override
	public int externalState() throws Exception {
		return getState();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Integer getOffspringCount() {
		return getMemory().getOffspringCount();
	}

	public void setOffspringCount(Integer offspringCount) {
		getMemory().setOffspringCount(offspringCount);
	}
	
	public void incrementOffspring() {
		setOffspringCount(getOffspringCount()+1);
	}
	
	public void birth() {
		incrementOffspring();
		setAgeAtWhichWeHadLastChild(getAge());
	}

	public Integer getAge() {
		return getMemory().getAge();
	}

	public void setAge(Integer age) {
		getMemory().setAge(age);
	}

	public Integer getAgeAtWhichWeHadLastChild() {
		return getMemory().getAgeAtWhichWeHadLastChild();
	}

	public void setAgeAtWhichWeHadLastChild(Integer ageAtWhichWeHadLastChild) {
		getMemory().setAgeAtWhichWeHadLastChild(ageAtWhichWeHadLastChild);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public OrganismsGame getGame() {
		return game;
	}

	public void setGame(OrganismsGame game) {
		this.game = game;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MoveFactory getMoveFactory() {
		return moveFactory;
	}

	public void setMoveFactory(MoveFactory moveFactory) {
		this.moveFactory = moveFactory;
	}

	public Random getRand() {
		return rand;
	}

	protected void setRand(Random rand) {
		this.rand = rand;
	}
	
	public Boolean nOutOfMTimes(int n, int m) {
		return PlayerUtil.nOutOfMTimes(n, m, rand);
	}
	
	public Integer nextRandomInt(int max) {
		return rand.nextInt(max);
	}
		
	public Double factorOfMaximumEnergyPerOrganism(Double factor) {
		return getMaximumEnergyPerOrganismM() * factor;
	}
	
	public Integer getMaximumEnergyPerOrganismM() {
		return getGame().M();
	}
	
	public Integer getEnergyConsumedByMovingOrReproducingV() {
		return getGame().v();
	}
	
	public Integer getEnergyPerUnitOfFoodU() {
		return getGame().u();
	}
	
	public Integer getMaximumFoodUnitsPerCellK(){
		return getGame().K();
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
}
