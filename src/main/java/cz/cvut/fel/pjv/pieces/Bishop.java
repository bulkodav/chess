package cz.cvut.fel.pjv.pieces;

import com.google.common.collect.ImmutableList;
import cz.cvut.fel.pjv.Colour;
import cz.cvut.fel.pjv.board.Board;
import cz.cvut.fel.pjv.board.Move;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {


    public Bishop(int pieceRow, int pieceColumn, Colour pieceColour) {
        super(pieceRow, pieceColumn, pieceColour);
    }

    @Override
    public Collection<Move> calculateMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }
}