package me.elb1to.souppvp.utils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command Framework - BukkitCommand
 * An implementation of Bukkit's Command class allowing for registering of commands without plugin.yml
 *
 * @author minnymin3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    public String name();

    public String permission() default "";

    public String[] aliases() default {};

    public String description() default "";

    public String usage() default "";

    public boolean inGameOnly() default true;
}

