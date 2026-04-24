package net.hazen.elemental_synergies.Registries;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ESDataAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
			DeferredRegister.create(net.neoforged.neoforge.registries.NeoForgeRegistries.ATTACHMENT_TYPES, "elemental_synergies");

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> HOLY_FIRE_TICKS =
			ATTACHMENTS.register("holy_fire_ticks",
					() -> AttachmentType.builder(() -> 0)
							.serialize(Codec.INT)
							.build()
			);
}