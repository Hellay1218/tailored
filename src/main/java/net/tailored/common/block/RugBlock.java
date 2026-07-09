package net.tailored.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tailored.common.register.TailoredBlocks;
import net.tailored.common.register.TailoredItems;

import java.util.Map;
import java.util.Objects;
import java.util.function.IntFunction;

public class RugBlock extends Block {

    public static final MapCodec<RugBlock> CODEC = simpleCodec(RugBlock::new);
    private static final VoxelShape SHAPE = Block.column(16.0, 0.0, 1.0);

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final EnumProperty<RugBlock.ConnectMode> CONNECT_MODE = TailoredBlocks.CONNECT_MODE;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap.copyOf(
            Maps.newEnumMap(Map.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST)).entrySet()
                    .stream()
                    .filter(entry -> (entry.getKey()).getAxis().isHorizontal())
                    .collect(Util.toMap()));

    public RugBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(CONNECT_MODE, RugBlock.ConnectMode.All)
        );
    }

    @Override
    public MapCodec<? extends RugBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected BlockState updateShape(
            BlockState blockState,
            LevelReader levelReader,
            ScheduledTickAccess scheduledTickAccess,
            BlockPos blockPos,
            Direction direction,
            BlockPos blockPos2,
            BlockState blockState2,
            RandomSource randomSource
    ) {
        return direction.getAxis().isHorizontal()
                ? blockState.setValue(
                PROPERTY_BY_DIRECTION.get(direction),
                connectsTo(blockState2 , blockState)
        )
                : super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
    }

//    @Override
//    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
//        if (itemStack.is(TailoredItems.SCISSORS)){
//            if (player instanceof ServerPlayer) {
//                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
//            }
//            BlockState north = level.getBlockState(blockPos.north());
//            BlockState east = level.getBlockState(blockPos.east());
//            BlockState south = level.getBlockState(blockPos.south());
//            BlockState west = level.getBlockState(blockPos.west());
//
//            BlockState blockState2 = blockState.setValue(CONNECT_MODE, blockState.getValue(CONNECT_MODE).getNext());
//            BlockState blockState3 = blockState2
//                    .setValue(NORTH, connectsTo(north , blockState2))
//                    .setValue(EAST, connectsTo(east , blockState2))
//                    .setValue(SOUTH, connectsTo(south , blockState2))
//                    .setValue(WEST, connectsTo(west , blockState2));
//
//            level.setBlockAndUpdate(blockPos, blockState3);
//            level.playSound(player, blockPos, SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS, 1.0F, 1.0F);
//            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState2));
//
//            return InteractionResult.SUCCESS;
//        }
//        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
//    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {

        BlockGetter blockGetter = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();

        BlockState self = blockGetter.getBlockState(blockPos);
        BlockState north = blockGetter.getBlockState(blockPos.north());
        BlockState east = blockGetter.getBlockState(blockPos.east());
        BlockState south = blockGetter.getBlockState(blockPos.south());
        BlockState west = blockGetter.getBlockState(blockPos.west());

        return Objects.requireNonNull(super.getStateForPlacement(blockPlaceContext))
                .setValue(NORTH, connectsTo(north , self))
                .setValue(EAST, connectsTo(east , self))
                .setValue(SOUTH, connectsTo(south , self))
                .setValue(WEST, connectsTo(west , self));
    }

    public static boolean connectsTo(BlockState neighbor, BlockState self) {
        boolean connects = false;

        if (self.getBlock() instanceof RugBlock){
            if(self.getValue(CONNECT_MODE) == (RugBlock.ConnectMode.All)) {
                connects = neighbor.getBlock() instanceof RugBlock;
            }
            else if (self.getValue(CONNECT_MODE) == (ConnectMode.COLOR)){
                connects = neighbor.getBlock() == self.getBlock();
            }
        }
        return connects;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, CONNECT_MODE);
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return !levelReader.isEmptyBlock(blockPos.below());
    }

    public enum ConnectMode implements StringRepresentable {
        All("all"),
        COLOR("color"),
        NONE("none");
        public static final IntFunction<ConnectMode> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private final String name;

        ConnectMode(final String string2) {
            this.name = string2;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
        public ConnectMode getNext(){
            return BY_ID.apply(this.ordinal() + 1);
        }
    }
}
