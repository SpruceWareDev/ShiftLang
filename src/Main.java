import dev.spruce.shiftlang.Interpreter;
import dev.spruce.shiftlang.Lexer;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.parsing.BooleanExpressionParser;
import dev.spruce.shiftlang.parsing.ForLoopExpressionParser;
import dev.spruce.shiftlang.parsing.NumberExpressionParser;
import dev.spruce.shiftlang.parsing.StringExpressionParser;
import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;
import dev.spruce.shiftlang.util.ForLoopData;
import dev.spruce.shiftlang.util.ProgramTimer;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ProgramTimer timer = new ProgramTimer();

        List<InstructionData> parsedInstructions = Lexer.parseInstructionsFromFile("code/fibonacci.sft");
        Interpreter interpreter = new Interpreter(parsedInstructions);
        try {
            interpreter.execute();
        } catch (RuntimeException e) {
            System.err.println("Programmed encountered an error on instruction " + interpreter.getCurrentLine());
            e.printStackTrace();
            return;
        }

        System.out.println();
        System.out.println("Program finished executing!");
        System.out.println("Runtime: " + timer.getRuntime() + "ms.");
    }
}
