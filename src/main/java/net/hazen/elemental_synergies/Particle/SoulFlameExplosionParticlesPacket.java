package net.hazen.elemental_synergies.Particle;

import net.hazen.elemental_synergies.Setup.ESClientSpellCastHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SoulFlameExplosionParticlesPacket implements CustomPacketPayload {
    private final Vec3 pos1;
    private final float radius;
    public static final Type<SoulFlameExplosionParticlesPacket> TYPE = new Type(ResourceLocation.fromNamespaceAndPath("elemental_synergies", "soul_flame_explosion"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SoulFlameExplosionParticlesPacket> STREAM_CODEC = CustomPacketPayload.codec(SoulFlameExplosionParticlesPacket::write, SoulFlameExplosionParticlesPacket::new);

    public SoulFlameExplosionParticlesPacket(Vec3 pos1, float radius) {
        this.pos1 = pos1;
        this.radius = radius;
    }

    public SoulFlameExplosionParticlesPacket(FriendlyByteBuf buf) {
        this.pos1 = buf.readVec3();
        this.radius = buf.readFloat();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVec3(this.pos1);
        buf.writeFloat(this.radius);
    }

    public static void handle(SoulFlameExplosionParticlesPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> ESClientSpellCastHelper.soulFlameExplosion(packet.pos1, packet.radius));
    }

    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
