package cz.cvut.fel.pjv.engine.pieces;

import com.google.common.collect.ImmutableList;
import cz.cvut.fel.pjv.engine.Colour;
import cz.cvut.fel.pjv.engine.board.Board;
import cz.cvut.fel.pjv.engine.board.moves.Move;

import java.util.Collection;

public class Rook extends Piece{

    public Rook(
                int pieceRow,
                int pieceColumn,
                Colour pieceColour) {
        super(PieceType.ROOK, pieceRow, pieceColumn, pieceColour);
    }

    @Override
    public Collection<Move> calculateMoves(final Board board) {
        return ImmutableList.copyOf(PieceMoveCalculator.calculateStraightSliderMoves(
                board, this, 8));
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }
}
