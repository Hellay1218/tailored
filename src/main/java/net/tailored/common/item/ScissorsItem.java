package net.tailored.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.tailored.common.block.RugBlock;

public class ScissorsItem extends Item {
    public ScissorsItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        level.playSound(player, player.blockPosition(), SoundEvents.SNOW_GOLEM_SHEAR, SoundSource.PLAYERS, 1.0F, 1.5F);
        return super.use(level, player, interactionHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = useOnContext.getLevel().getBlockState(blockPos);
        if (blockState.getBlock() instanceof RugBlock){
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, useOnContext.getItemInHand());
            }
            BlockState north = level.getBlockState(blockPos.north());
            BlockState east = level.getBlockState(blockPos.east());
            BlockState south = level.getBlockState(blockPos.south());
            BlockState west = level.getBlockState(blockPos.west());

            BlockState blockState2 = blockState.setValue(RugBlock.CONNECT_MODE, blockState.getValue(RugBlock.CONNECT_MODE).getNext());
            BlockState blockState3 = blockState2
                    .setValue(RugBlock.NORTH, RugBlock.connectsTo(north , blockState2))
                    .setValue(RugBlock.EAST, RugBlock.connectsTo(east , blockState2))
                    .setValue(RugBlock.SOUTH, RugBlock.connectsTo(south , blockState2))
                    .setValue(RugBlock.WEST, RugBlock.connectsTo(west , blockState2));

            level.setBlockAndUpdate(blockPos, blockState3);
            level.playSound(player, blockPos, SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS, 1.0F, 1.2F);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState2));

            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }
}
