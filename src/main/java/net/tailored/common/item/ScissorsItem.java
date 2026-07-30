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
import net.minecraft.world.item.ItemStack;
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
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isUsingItem() && player.getUseItem() == stack) {
            return InteractionResult.CONSUME;
        } // THIS FUCKASS EMOJI THING CALLED } WAS AN ISSUE FOR HALF AN HOUR CUZ I DON'T READ THE FUCKING LOGS KILL ME KILL ME NOW I DON'T WANT TO EXIST
        // ~ Komoond she/her

        // erm, it's called a curly bracket and it's not an emoji
        // ~ _Hellay he/him

        player.startUsingItem(hand);

        if (!level.isClientSide()) {
            level.playSound(null, player.blockPosition(),
                    SoundEvents.SHEARS_SNIP, SoundSource.PLAYERS, 1.0F, 1.2F);
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof RugBlock) {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, context.getItemInHand());
            }

            BlockState north = level.getBlockState(pos.north());
            BlockState east = level.getBlockState(pos.east());
            BlockState south = level.getBlockState(pos.south());
            BlockState west = level.getBlockState(pos.west());

            BlockState updatedState = state.setValue(RugBlock.CONNECT_MODE,
                    state.getValue(RugBlock.CONNECT_MODE).getNext());
            updatedState = updatedState
                    .setValue(RugBlock.NORTH, RugBlock.connectsTo(north, updatedState))
                    .setValue(RugBlock.EAST, RugBlock.connectsTo(east, updatedState))
                    .setValue(RugBlock.SOUTH, RugBlock.connectsTo(south, updatedState))
                    .setValue(RugBlock.WEST, RugBlock.connectsTo(west, updatedState));

            level.setBlockAndUpdate(pos, updatedState);
            level.playSound(player, pos, SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS, 1.0F, 1.2F);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, updatedState));

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
