package noobanidus.mods.grindr.registrate;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipeSerializer;

import java.util.function.Supplier;

public class ContainerBuilder<T extends Container, P> extends AbstractBuilder<ContainerType<?>, ContainerType<T>, P, ContainerBuilder<T, P>> {
  private final ContainerType.IFactory<T> factory;

  public ContainerBuilder(CustomRegistrate owner, P parent, String name, BuilderCallback callback, ContainerType.IFactory<T> factory) {
    super(owner, parent, name, callback, ContainerType.class);
    this.factory = factory;
  }

  @Override
  protected ContainerType<T> createEntry() {
    return new ContainerType<>(factory);
  }
}
