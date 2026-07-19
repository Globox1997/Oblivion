package net.oblivion.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.oblivion.block.entity.MultiOreBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MultiOreBlock extends BlockWithEntity {

    private final int drillCount;

    public MultiOreBlock(int drillCount, Settings settings) {
        super(settings);
        this.drillCount = drillCount;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        if (!player.isCreative()) {
            player.sendMessage(Text.translatable("block.multi_ore.drill"), true);
        }
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public void breakMultiOre(World world, BlockPos pos, @Nullable PlayerEntity player) {
        if (!world.isClient()) {
            for (BlockPos blockPos : getMultiOreBlockPoses(world, pos)) {
                world.breakBlock(blockPos, true, player);
            }
        }
    }

    public List<BlockPos> getMultiOreBlockPoses(World world, BlockPos startPos) {
        Set<BlockPos> visited = new HashSet<>();
        List<BlockPos> poses = new ArrayList<>();
        Stack<BlockPos> stack = new Stack<>();
        stack.push(startPos);

        while (!stack.isEmpty()) {
            BlockPos currentPos = stack.pop();

            if (visited.contains(currentPos)) {
                continue;
            }

            visited.add(currentPos);

            if (!world.getBlockState(currentPos).isOf(this.asBlock())) {
                continue;
            }

            poses.add(currentPos);

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        if (dx == 0 && dy == 0 && dz == 0) {
                            continue;
                        }

                        BlockPos neighborPos = currentPos.add(dx, dy, dz);

                        if (!visited.contains(neighborPos)) {
                            stack.push(neighborPos);
                        }
                    }
                }
            }
        }

        return poses;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MultiOreBlockEntity(pos, state, this.drillCount);
    }
}
